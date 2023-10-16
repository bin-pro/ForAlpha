package com.example.legendfive.overall.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.example.legendfive.overall.Entity.PredictionRecord;
import com.example.legendfive.overall.Entity.Stock;
import com.example.legendfive.overall.Entity.ThemeCard;
import com.example.legendfive.overall.Entity.User;
import com.example.legendfive.overall.dto.StockDto;
import com.example.legendfive.overall.repository.PredictionRecordRepository;
import com.example.legendfive.overall.repository.ThemeCardRepository;
import com.example.legendfive.overall.repository.UserRepository;
import com.example.legendfive.overall.repository.stock.StockRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.text.DecimalFormat;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StockService {

    private static final String S3_BUCKET_NAME = "api-test01";
    private static final String S3_FILE_PATH = "todayStock/";
    private final AmazonS3 amazonS3;
    private final StockRepository stockRepository;
    private final UserRepository userRepository;
    private final PredictionRecordRepository predictionRecordRepository;
    private final ThemeCardRepository themeCardRepository;

    /**
     * 검색 리스트에서 세부 종목을 하나 눌렀을때, S3에서 값을 가져와서 프론트로 전해줄 값
     * S3에 저장된 오늘 날짜의 주식 정보를 가져오는 메소드 -> 아침마다 예측에 사용
     */
    public StockDto.stockDetailResponseDto getStockDetails(String stockName) {

        Stock stock = stockRepository.findByStockName(stockName).orElseThrow(
                () -> {
                    throw new RuntimeException("해당 주식이 없습니다.");
                }
        );

        try {

            S3Object s3Object = amazonS3.getObject(S3_BUCKET_NAME, S3_FILE_PATH + stock.getStockCode() + ".json");
            System.out.println(s3Object);
            S3ObjectInputStream s3ObjectInputStream = s3Object.getObjectContent();

            String jsonContent = new BufferedReader(new InputStreamReader(s3ObjectInputStream))
                    .lines().collect(Collectors.joining("\n"));

            ObjectMapper objectMapper = new ObjectMapper();
            StockDto.stockDetailResponseDto stockDetail = objectMapper.readValue(jsonContent, StockDto.stockDetailResponseDto.class);

            s3ObjectInputStream.close();
            return stockDetail;

        } catch (AmazonS3Exception | IOException e) {
            e.printStackTrace();
            throw new AmazonS3Exception("Amazon S3에서 파일을 읽을 수 없습니다.");
        }
    }

    /**
     * 모든 유저를 매일 아침마다 주식이 올랐는지 확인하여 포인트 지급 로직
     */
    @Transactional
    @Scheduled(cron = "5 0 9 * * ?")
    public void checkIsStockUp() {


        //1. 모든 유저를 가져온다
        for (User user : userRepository.findAll()) {

            //2. 모든 유저의 예측 기록을 가져온다
            for (PredictionRecord predictionRecord : predictionRecordRepository.findByUser(user)) {

                //3. 예측 기록의 종료일이 오늘인지 확인한다
                if (Objects.equals(predictionRecord.getEndDay(), LocalDate.now())) {

                    //4. 종료일이 오늘이라면, S3에서 해당 주식의 현재 가격을 가져온다
                    StockDto.stockDetailResponseDto stockDetailResponseDto = getStockDetails(predictionRecord.getStockCode().toString());
                    String stockEndPriceFromS3 = stockDetailResponseDto.getStock_present_price();

                    // 예측 설정한 기간을 가져옴
                    int investmentPeriod = predictionRecord.getEndDay().getDayOfYear() - predictionRecord.getCreatedAt().getDayOfYear();

                    //수익률(+,-)
                    double earningRate = (double) (Integer.parseInt(stockEndPriceFromS3) - predictionRecord.getStockPresentPrice()) / predictionRecord.getStockPresentPrice();

                    // DecimalFormat을 사용하여 소수점 이하 숫자를 제외하고 출력
                    DecimalFormat decimalFormat = new DecimalFormat("#");
                    String formattedEarningRate = decimalFormat.format(earningRate * 100);

                    //계산된 포인트
                    int calculatedTotalPrice = calculateTotalPrice(investmentPeriod, earningRate);

                    if (user.getUserPoint() + calculatedTotalPrice < 0) {
                        user.updateUserPoint(0);
                    } else {
                        user.updateUserPoint(user.getUserPoint() + calculatedTotalPrice);
                    }

                    //예측하기 성공시, 테마 카드 저장
                    if (earningRate > 0) {
                        //테마 이름 가져옴
                        String themeName = stockDetailResponseDto.getStock_theme_code();

                        ThemeCard newThemeCard = ThemeCard.builder()
                                .themeName(themeName)
                                .createdAt(LocalDate.now())
                                .user(user)
                                .build();

                        themeCardRepository.save(newThemeCard);
                    }

                    predictionRecord.updateStockEndPriceIncreateRate(Integer.parseInt(stockEndPriceFromS3), formattedEarningRate, String.valueOf(calculatedTotalPrice));
                }
            }
        }

    }

    /**
     * 포인트 계산 로직
     **/
    public int calculateTotalPrice(int investmentPeriod, double earningRate) {

        int basePoint = 100;
        double additionalPercentage = 100;
        int totalPoint = 0;

//        // 수익률을 양수와 음수로 구분
//        if (investmentPeriod <= 1) {
//            // 단타(1일)의 경우
//            additionalPercentage = 200;
//        } else if (investmentPeriod <= 90) {
//            int maxK = 200;
//            int minK = 5;
//            int daysAfter1 = investmentPeriod - 1;
//            double K = maxK - (daysAfter1 * (maxK - minK) / 89);
//
//            additionalPercentage = K;
//        }

        // 수익률이 양수인 경우
        if (earningRate > 0) {
            totalPoint = basePoint + (int) (earningRate * additionalPercentage);
        }
        // 수익률이 음수인 경우
        else if (earningRate < 0) {
            totalPoint = -basePoint + (int) (earningRate * additionalPercentage);
        }

        return totalPoint;
    }

    /**
     * 주식 예측하기
     **/
    @Transactional
    public StockDto.stockPredictionResponseDto predictStock(String stockCode, UUID
            userUUID, StockDto.stockPredictionRequsetDto stockPredictionRequsetDto) {

        Stock stock = stockRepository.findByStockCode(stockCode).orElseThrow(
                () -> new IllegalArgumentException("해당 주식이 존재하지 않습니다.")
        );

        User user = userRepository.findByUserId(userUUID).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        PredictionRecord existingPrediction = predictionRecordRepository.findByStockAndUserAndCreatedAt(stock, user, LocalDate.now()).orElse(null);

        if (existingPrediction != null) {
            return StockDto.stockPredictionResponseDto.builder()
                    .message("이미 해당 주식을 오늘 예측한 적이 있습니다.")
                    .build();
        }

        //포인트가 있는지 확인 -> 예측하기 하려면 100포인트가 필요
        if (user.getUserPoint() <= 0 || user.getUserPoint() - 100 < 0) {
            return StockDto.stockPredictionResponseDto.builder()
                    .message("포인트가 부족합니다.")
                    .build();
        }

        //주식 예측 기록 저장
        LocalDate now = LocalDate.now();
        LocalDate end_day = now.plusDays(Long.parseLong(stockPredictionRequsetDto.getInvestment_period()));

        PredictionRecord predictionRecord = PredictionRecord.builder()
                .stock(stock)
                .user(user)
                .stockPresentPrice(Integer.parseInt(stockPredictionRequsetDto.getStock_present_price()))
                .predictionRecordUuid(UUID.randomUUID())
                .endDay(end_day)
                .isPublic(true)
                .stockEarnedPoint("0")
                .stockIncreaseRate("0")
                .stockCode(stock.getStockCode()).build();

        predictionRecordRepository.save(predictionRecord);

        //user에서 포인트 차감 로직
        user.updateUserPoint(user.getUserPoint() - 100);

        return StockDto.stockPredictionResponseDto.builder()
                .message("주식 예측 기록 저장 완료")
                .build();

    }

    public Page<StockDto.SearchStockBrandResponseDto> searchStockByBrandName(String brandName, Pageable pageable) {
        Page<Stock> searchResults = stockRepository.findByStockNameContainingIgnoreCase(brandName, pageable);

        // Page를 DTO로 변환
        return searchResults.map(stockEntity -> StockDto.SearchStockBrandResponseDto.builder()
                .StockName(stockEntity.getStockName())
                .build());
    }

    public Page<StockDto.SearchStockBrandResponseDto> searchStockByThemeName(String themeName, Pageable pageable) {
        Page<Stock> searchResults = stockRepository.findByThemeNameContainingIgnoreCase(themeName, pageable);

        // Page를 DTO로 변환
        return searchResults.map(stockEntity -> StockDto.SearchStockBrandResponseDto.builder()
                .StockName(stockEntity.getStockName())
                .build());
    }
}
