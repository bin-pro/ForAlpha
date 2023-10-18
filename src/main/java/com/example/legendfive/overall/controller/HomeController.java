package com.example.legendfive.overall.controller;

import com.example.legendfive.common.response.ResponseDto;
import com.example.legendfive.overall.Service.HomeService;
//import com.example.legendfive.overall.Service.VolumesTestService;
import com.example.legendfive.overall.dto.HomeDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/foralpha-service/home")
public class HomeController {

    private final HomeService homeService;
    private final ObjectMapper objectMapper;
//    private final VolumesTestService volumesTestService;

    /**
     * 레디스를 사용하여 거래량 정보 가져오기
     * **/
    @GetMapping("/trading-volumes")
    public ResponseEntity<ResponseDto> tradingVolume() {

        try {
            HomeDto.volumeReseponseDto tradingFromCache = homeService.getTradingFromRedis();

            ResponseDto responseDto = ResponseDto.builder()
                    .payload(objectMapper.convertValue(tradingFromCache, Map.class))
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        } catch (IllegalArgumentException e) {
            ResponseDto responseDto = ResponseDto.builder().error("Can't get trandingVolumes from cache").build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
//
//    /***
//     * 한투 open api로부터 바로 거래량 데이터 가져오기
//     * **/
//    @GetMapping("/test/trading-volumes")
//    public ResponseEntity<ResponseDto> tradingVolumeFromOpenApi() {
//
//        try {
//            HomeDto.volumeReseponseDto tradingFromCache = volumesTestService.getTradingVolumesFromOpenApi().block();
//
//            ResponseDto responseDto = ResponseDto.builder()
//                    .payload(objectMapper.convertValue(tradingFromCache, Map.class))
//                    .build();
//            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
//        } catch (IllegalArgumentException e) {
//            ResponseDto responseDto = ResponseDto.builder().error("Can't get trandingVolumes from cache").build();
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
//        }
//    }
}