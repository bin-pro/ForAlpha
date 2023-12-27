package com.example.legendfive.overall.controller;

import com.example.legendfive.common.response.ResponseDto;
import com.example.legendfive.overall.Service.StockService;
import com.example.legendfive.overall.dto.StockDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/foralpha-service/stocks")
public class StockController {

    private final ObjectMapper objectMapper;
    private final StockService stockService;
    final List<String> themes = List.of("화학", "반도체", "제약", "기계·장비", "소프트웨어", "금융", "서비스", "IT부품", "유통", "전기전자", "의료·정밀기기");



    @GetMapping("/checkPredict")
    public void checkPredict(){
        stockService.checkIsStockUp();
    }


    /**
     * 검색 리스트에서 세부 종목을 하나 눌렀을때, S3에서 값을 가져와서 프론트로 전해줄 값
     */
    @GetMapping("/{stock_code}/details")
    public ResponseEntity<ResponseDto> getStockDetails(@PathVariable("stock_code") String stockCode) {

        try {
            StockDto.stockDetailResponseDto stockDetailResponseDto = stockService.getStockDetailsFromS3(stockCode);

            ResponseDto responseDto2 = ResponseDto.builder()
                    .payload(objectMapper.convertValue(stockDetailResponseDto, Map.class))
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(responseDto2);

        } catch (Exception e) {
            ResponseDto responseDto = ResponseDto.builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
        }
    }

    /**
     * 개별 주식 예측하기를 눌렀을 때, DB에 저장
     * **/
    @PostMapping("")
    public ResponseEntity<ResponseDto> recordPredict(@RequestBody StockDto.stockPredictionRequsetDto stockPredictionRequsetDto) {
        try {
            StockDto.stockPredictionResponseDto stockPredictionResponseDto = stockService.predictStock(stockPredictionRequsetDto);
            ResponseDto responseDto2 = ResponseDto.builder()
                    .payload(objectMapper.convertValue(stockPredictionResponseDto, Map.class))
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(responseDto2);
        } catch (Exception e) {
            ResponseDto responseDto = ResponseDto.builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
        }
    }

    @GetMapping("/point/stock/brand-search")
    public ResponseEntity<ResponseDto> searchStockBrand(@RequestParam("stock-brand-name") String brandName,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<StockDto.SearchStockBrandResponseDto> searchResults = stockService.searchStockByBrandName(brandName, pageable);

            // 정상적인 응답 생성
            ResponseDto successResponse = ResponseDto.builder()
                    .payload(Collections.singletonMap("stocks", searchResults.getContent()))
                    .build();

            return new ResponseEntity<>(successResponse, HttpStatus.OK);
        } catch (Exception e) {
            // 예외 발생 시 에러 응답
            ResponseDto errorResponse = ResponseDto.builder()
                    .error(e.getMessage())
                    .build();

            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 테마 상위 15개 아니면 오류 반환
    @GetMapping("/point/stock/theme-search")
    public ResponseEntity<ResponseDto> searchStockTheme(@RequestParam("stock-theme-name") String themeName,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size) {
        //화학, 반도체, 제약, 기계·장비, 소프트웨어, 금융, 서비스, IT부품, 유통, 전기전자, 의료·정밀기기
        //화학, 반도체, 제약, 기계·장비, 소프트웨어, 금융, 기타서비스, 서비스업, IT부품, 유통, 기타금융, 전기전자, 의료·정밀기기, 유통업, 일반전기전자

        try {
            if(!themes.contains(themeName)){
                ResponseDto responseDto = ResponseDto.builder()
                        .error("검색되지 않는 테마")
                        .build();
                return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
            }

            Pageable pageable = PageRequest.of(page, size);

            Page<StockDto.SearchStockThemeResponseDto> searchResults = stockService.searchStockByThemeName(themeName, pageable);

            // 정상적인 응답 생성
            ResponseDto successResponse = ResponseDto.builder()
                    .payload(Collections.singletonMap("stocks", searchResults.getContent()))
                    .build();
            return new ResponseEntity<>(successResponse, HttpStatus.OK);
        } catch (Exception e) {
            // 예외 발생 시 에러 응답
            ResponseDto errorResponse = ResponseDto.builder()
                    .error(e.getMessage())
                    .build();

            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}