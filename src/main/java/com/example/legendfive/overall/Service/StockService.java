package com.example.legendfive.overall.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.example.legendfive.overall.Entity.PredictionRecord;
import com.example.legendfive.overall.Entity.Stock;
import com.example.legendfive.overall.Entity.User;
import com.example.legendfive.overall.dto.StockDto;
import com.example.legendfive.overall.repository.PredictionRecordRepository;
import com.example.legendfive.overall.repository.UserRepository;
import com.example.legendfive.overall.repository.stock.StockRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;

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

    /**
     * 검색 리스트에서 세부 종목을 하나 눌렀을때, S3에서 값을 가져와서 프론트로 전해줄 값
     */
    public StockDto.stockDetailResponseDto getStockDetails(String stockId) {
        try {

            S3Object s3Object = amazonS3.getObject(S3_BUCKET_NAME, S3_FILE_PATH + stockId + ".json");
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
                if (predictionRecord.getEndDay().getDayOfYear() == LocalDateTime.now().getDayOfYear()) {

                    //4. 종료일이 오늘이라면, S3에서 해당 주식의 현재 가격을 가져온다
                    StockDto.stockDetailResponseDto stockDetailResponseDto = getStockDetails(predictionRecord.getStockCode().toString());
                    String stockPresentPriceInS3 = stockDetailResponseDto.getStock_present_price();

                    //5. 현재 가격이 DB에 저장된 예측 가격보다 높다면, 포인트를 지급한다
                    if (Integer.parseInt(stockPresentPriceInS3) > predictionRecord.getStock_present_price()) {

                        //수익률 계산 로직
                        int earningRate = (Integer.parseInt(stockPresentPriceInS3) - predictionRecord.getStock_present_price()) / predictionRecord.getStock_present_price();
                        int ratePlusPoint = earningRate * 200;

                        user.updateUserPoint(user.getUserPoint() + 100 + ratePlusPoint);

                    } else {
                        user.updateUserPoint(user.getUserPoint() - 100);
                    }
                }
            }
        }

    }

    /**
     * 주식 예측하기
     **/
    @Transactional
    public StockDto.stockPredictionResponseDto predictStock(UUID stockUUID, UUID userUUID, StockDto.stockPredictionRequsetDto stockPredictionRequsetDto) {

        Stock stock = stockRepository.findByStockUuid(stockUUID).orElseThrow(
                () -> new IllegalArgumentException("해당 주식이 존재하지 않습니다.")
        );

        User user = userRepository.findByUserUuid(userUUID).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        //오늘 해당 주식을 예측한 적이 있는지 확인
        if (predictionRecordRepository.findByStockAndUser(stock, user).isPresent()) {
            return StockDto.stockPredictionResponseDto.builder()
                    .message("이미 해당 주식을 예측한 적이 있습니다.")
                    .build();
        }

        //주식 예측 기록 저장
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime end_day = now.plusDays(Long.parseLong(stockPredictionRequsetDto.getInvestment_period()));

        PredictionRecord predictionRecord = PredictionRecord.builder()
                .stock(stock)
                .user(user)
                .stock_present_price(Integer.parseInt(stockPredictionRequsetDto.getStock_present_price()))
                .predictionRecordUuid(UUID.randomUUID())
                .endDay(end_day)
                .stockCode(String.valueOf(Long.parseLong(stock.getStockCode()))).build();

        predictionRecordRepository.save(predictionRecord);

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
