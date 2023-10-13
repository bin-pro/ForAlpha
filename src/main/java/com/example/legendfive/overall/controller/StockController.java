//package com.example.legendfive.overall.controller;
//
//import com.example.legendfive.common.response.ResponseDto;
////import com.example.legendfive.overall.Service.StockService;
//import com.example.legendfive.overall.Entity.Stock;
//import com.example.legendfive.overall.Service.StockService;
//import com.example.legendfive.save.service.StockThemeService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Controller
//@RequiredArgsConstructor
//@RequestMapping("/stocks")
//public class StockController {
//
//    private final ObjectMapper objectMapper;
//
//    private final StockService stockService;
//
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
//}
