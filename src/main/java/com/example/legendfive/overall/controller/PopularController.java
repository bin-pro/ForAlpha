//package com.example.legendfive.overall.controller;
//
////import com.example.legendfive.overall.Service.HomeService;
//import com.example.legendfive.overall.Service.HomeService;
//import com.example.legendfive.overall.Service.PopularService;
//import com.example.legendfive.overall.dto.FriendDto;
//import com.example.legendfive.overall.dto.HomeDto;
//import com.example.legendfive.overall.dto.ResponseDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.UUID;
//
//@RestController
//@RequiredArgsConstructor
//public class PopularController {
//
//    private final PopularService popularService;
//    @GetMapping("/popular")
//    public ResponseEntity<ResponseDto> getPopularStocks() {
////        try {
////            List<HomeDto.popularStockResponseDto> popularStocks = predictionService.getPopularStocks();
////
////            Map<String, Object> payload = new HashMap<>();
////            payload.put("searchResults", popularStocks);
////
////            ResponseDto responseBody = ResponseDto.builder()
////                    .payload(payload)
////                    .build();
////
////            return new ResponseEntity<>(responseBody, HttpStatus.OK);
////        } catch (Exception e) {
////            ResponseDto errorResponse = ResponseDto.builder()
////                    .error(e.getMessage())
////                    .build();
////            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
////        }
//        try {
//            List<HomeDto.popularStockResponseDto> popularStocks = popularService.getPopularStocks();
//
//            Map<String, Object> payload = new HashMap<>();
//            payload.put("popularStocks", popularStocks);
//
//            ResponseDto responseBody = ResponseDto.builder()
//                    .payload(payload)
//                    .build();
//
//            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
//        } catch (RuntimeException e) {
//            ResponseDto errorResponse = ResponseDto.builder()
//                    .error(e.getMessage())
//                    .build();
//
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
//        }
//    }
//
//}
