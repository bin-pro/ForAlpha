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
import java.util.UUID;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stocks")
public class StockController {

    private final ObjectMapper objectMapper;
    private final StockService stockService;


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
            StockDto.stockDetailResponseDto stockDetailResponseDto = stockService.getStockDetails(stockCode);

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

    @GetMapping("/point/stock/theme-search")
    public ResponseEntity<ResponseDto> searchStockTheme(@RequestParam("stock-theme-name") String themeName,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<StockDto.SearchStockBrandResponseDto> searchResults = stockService.searchStockByThemeName(themeName, pageable);

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