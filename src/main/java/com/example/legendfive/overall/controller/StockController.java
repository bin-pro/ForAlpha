package com.example.legendfive.overall.controller;

import com.example.legendfive.common.response.ResponseDto;
import com.example.legendfive.overall.Service.StockService;
import com.example.legendfive.overall.dto.StockDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/stocks")
public class StockController {

    private final ObjectMapper objectMapper;
    private final StockService stockService;


    /**
     * 검색 리스트에서 세부 종목을 하나 눌렀을때, S3에서 값을 가져와서 프론트로 전해줄 값
     */
    @GetMapping("/{stock_id}")
    public ResponseEntity<ResponseDto> getStockDetails(@PathVariable("stock_id") String stockId) {

        try {
            StockDto.stockDetailResponseDto stockDetailResponseDto = stockService.getStockDetails(stockId);

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

    /*PathVariable로 user_id를 가져오는게 아니라, Spring Security나 @RequestAttribute로 가져와야하는데 뭐지???*/
    @PostMapping("/{stock_id}/users/{user_id}")
    public ResponseEntity<ResponseDto> recordPredict(@PathVariable("stock_id") UUID stockUUID, @PathVariable("user_id") UUID userUUID, @RequestBody StockDto.stockPredictionRequsetDto stockPredictionRequsetDto) {
        try {
            StockDto.stockPredictionResponseDto stockPredictionResponseDto = stockService.predictStock(stockUUID, userUUID, stockPredictionRequsetDto);
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


}

//    /**
//     * 검색 -> 종목
//     */
//    @GetMapping("/")
//    List<Stock> searchStocks(@RequestParam String stock) {
//
//        return stockService.findByStocks(stock);
//    }
//
//    /**
//     * 검색 -> 브랜드
//     */
//    @GetMapping("/")
//    List<Stock> searchTheme(@RequestParam String brand) {
//
//        return stockService.findBybrands(brand);
//    }

