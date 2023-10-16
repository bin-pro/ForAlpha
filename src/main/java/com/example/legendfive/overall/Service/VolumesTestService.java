package com.example.legendfive.overall.Service;

import com.example.legendfive.overall.dto.HomeDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class VolumesTestService {

    private final WebClient webClient;

    @Value("${openapi.appkey}")
    private String prodAppKey;
    @Value("${openapi.appsecretkey}")
    private String prodAppSecret;

    private static String accessToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0b2tlbiIsImF1ZCI6IjcyOWM1MGNhLTcyZjAtNDEyOS04YzQxLTA0NDViNjliNDcxYSIsImlzcyI6InVub2d3IiwiZXhwIjoxNjk3NTE5MzA2LCJpYXQiOjE2OTc0MzI5MDYsImp0aSI6IlBTS1liY3ZubFNUbnhXU1AxWnN0WXI2SDByemc1WVAzQXNKRiJ9.O0HbA99AhpE_GF6An9mekJSk4TXwPk5B2dHHuQXmeApkEOoforuxHRq4hBpTQBoBF77H-2o89d3EvD52NJi_nA";
    //private static String accessToken;

    /**
     * 주식의 거래량 정보들을 받아오기
     */
    public Mono<HomeDto.volumeReseponseDto> getTradingVolumesFromOpenApi() {
        return getVolumeRank(accessToken)
                .flatMap(data -> {
                    ObjectMapper objectMapper = new ObjectMapper();
                    try {
                        String dataJson = objectMapper.writeValueAsString(data);
                        JSONObject reformattedJson = jsonReformat(dataJson);
                        String tradingVolumes = reformattedJson.toJSONString();

                        objectMapper = new ObjectMapper();
                        HomeDto.volumeReseponseDto volumeReseponseDto = objectMapper.readValue(tradingVolumes, HomeDto.volumeReseponseDto.class);

                        return Mono.just(volumeReseponseDto);
                    } catch (JsonProcessingException | ParseException e) {
                        return Mono.error(e);
                    }
                })
                .doOnError(error -> log.error("데이터 가져오기 오류", error));
    }

    public JSONObject jsonReformat(String dataJson) throws ParseException {

        JSONParser parser = new JSONParser();
        JSONObject originalJsonObject = (JSONObject) parser.parse(dataJson);
        JSONArray outputArray = (JSONArray) originalJsonObject.get("output");

        JSONArray dataVolumes = new JSONArray();
        JSONObject newJsonObject = new JSONObject();

        for (Object item : outputArray) {
            JSONObject outputItem = (JSONObject) item;
            JSONObject priceDataItem = new JSONObject();

            priceDataItem.put("stock_name", outputItem.get("hts_kor_isnm"));
            priceDataItem.put("data_rank", outputItem.get("data_rank"));
            priceDataItem.put("stock_present_price", outputItem.get("stck_prpr"));
            priceDataItem.put("stock_dod_percentage", outputItem.get("prdy_ctrt"));
            priceDataItem.put("volumeIncreaseRate", outputItem.get("vol_inrt"));

            dataVolumes.add(priceDataItem);
        }
        newJsonObject.put("trading_volumes", dataVolumes);

        return newJsonObject;
    }

    public Mono<Object> getVolumeRank(String accessToken) {

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/uapi/domestic-stock/v1/quotations/volume-rank")
                        .queryParam("FID_COND_MRKT_DIV_CODE", "J")
                        .queryParam("FID_COND_SCR_DIV_CODE", "20171")
                        .queryParam("FID_INPUT_ISCD", "0002")
                        .queryParam("FID_DIV_CLS_CODE", "0")
                        .queryParam("FID_BLNG_CLS_CODE", "1")
                        .queryParam("FID_TRGT_CLS_CODE", "111111111")
                        .queryParam("FID_TRGT_EXLS_CLS_CODE", "000000")
                        .queryParam("FID_INPUT_PRICE_1", "0")
                        .queryParam("FID_INPUT_PRICE_2", "0")
                        .queryParam("FID_VOL_CNT", "20")
                        .queryParam("FID_INPUT_DATE_1", "0")
                        .build())
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)  // 올바른 헤더 이름을 사용
                .header("appkey", prodAppKey)  // 다른 사용자 지정 헤더
                .header("appsecret", prodAppSecret)  // 다른 사용자 지정 헤더
                .header("tr_id", "FHPST01710000")
                .retrieve()
                .bodyToMono(Object.class);
    }
}



