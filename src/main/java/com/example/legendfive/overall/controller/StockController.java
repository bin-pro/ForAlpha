package com.example.legendfive.overall.controller;

import com.example.legendfive.overall.Service.StockService;
import com.example.legendfive.overall.dto.FriendDto;
import com.example.legendfive.overall.dto.ResponseDto;
import com.example.legendfive.overall.dto.StockDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


@RestController
@RequiredArgsConstructor
@Slf4j
public class StockController {
    private final StockService stockService;

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
