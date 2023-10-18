package com.example.legendfive.overall.Service;

import com.example.legendfive.overall.Entity.Stock;
import com.example.legendfive.overall.dto.HomeDto;
import com.example.legendfive.overall.repository.stock.StockRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import com.example.legendfive.overall.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class HomeService {

    private final WebClient webClient;
    private final RedisTemplate<String, String> redisTemplate;
    private final StockRepository stockRepository;

    @Value("${openapi.appkey}")
    private String prodAppKey;
    @Value("${openapi.appsecretkey}")
    private String prodAppSecret;
    private static String accessToken;

    /**
     * 3초에 한 번씩 주식의 거래량 정보들을 받아오기, 받은 값들은 redis cash에 저장
     */
    @Scheduled(cron = "*/3 * * * * ?")
    public void getTradingVolumesSaveRedis() {

        if (accessToken == null) {
            accessToken = getAccessToken();
        }

        Mono<Object> mono = getVolumeRank(accessToken);

        mono.flatMap(data -> {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String dataJson = objectMapper.writeValueAsString(data);
                return Mono.just(dataJson);
            } catch (JsonProcessingException e) {
                log.error("데이터 JSON 변환 오류", e);
                return Mono.empty();
            }
        }).subscribe(
                dataJson -> {
                    try {
                        JSONObject reformattedJson = jsonReformat(dataJson);
                        ValueOperations<String, String> vop = redisTemplate.opsForValue();
                        vop.set("tradingVolumes", reformattedJson.toJSONString());
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                },
                error -> {
                    log.error("데이터 가져오기 오류", error);
                }
        );
    }

    /**
     * controller가 호출할 때마다 redis cash에 저장된 값을 가져오기
     */
    public HomeDto.volumeReseponseDto getTradingFromRedis() throws JsonProcessingException {

        ValueOperations<String, String> vop = redisTemplate.opsForValue();
        String tradingVolumes = vop.get("tradingVolumes");

        //mapper로 변환
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(tradingVolumes, HomeDto.volumeReseponseDto.class);
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


    @Scheduled(cron = "0 0 8 * * ?")
    public void getToken() {
        try {
            accessToken = getAccessToken();
        } catch (Exception e) {
            log.error("accessTokenError", e);
            throw new RuntimeException("Can't get accessToken");
        }
    }

    public String getAccessToken() {
        try {
            TokenResponse tokenResponse = webClient.post()
                    .uri("/oauth2/tokenP")
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .bodyValue("{\"grant_type\":\"client_credentials\",\"appkey\":\"" + prodAppKey + "\",\"appsecret\":\"" + prodAppSecret + "\"}")
                    .retrieve()
                    .bodyToMono(TokenResponse.class)
                    .block();

            if (tokenResponse != null) {
                return tokenResponse.getAccess_token();
            } else {
                log.error("Failed to retrieve access token: TokenResponse is null");
                return null;
            }
        } catch (Exception e) {
            log.error("Failed to retrieve access token: " + e.getMessage(), e);
            return null;
        }
    }
}









//package com.example.legendfive.overall.Service;
//
//        import com.example.legendfive.overall.dto.HomeDto;
//        import com.fasterxml.jackson.core.JsonProcessingException;
//        import com.fasterxml.jackson.databind.ObjectMapper;
//        import org.json.simple.JSONArray;
//        import org.json.simple.JSONObject;
//        import org.json.simple.parser.JSONParser;
//        import org.json.simple.parser.ParseException;
//        import org.springframework.data.redis.core.RedisTemplate;
//        import org.springframework.data.redis.core.ValueOperations;
//        import com.example.legendfive.overall.dto.TokenResponse;
//        import lombok.RequiredArgsConstructor;
//        import lombok.extern.slf4j.Slf4j;
//        import org.springframework.beans.factory.annotation.Value;
//        import org.springframework.http.HttpHeaders;
//        import org.springframework.http.MediaType;
//        import org.springframework.scheduling.annotation.Scheduled;
//        import org.springframework.stereotype.Service;
//        import org.springframework.web.reactive.function.client.WebClient;
//        import reactor.core.publisher.Mono;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class HomeService {
//
//    private final WebClient webClient;
//    private final RedisTemplate<String, String> redisTemplate;
//
//    @Value("${openapi.appkey}")
//    private String prodAppKey;
//    @Value("${openapi.appsecretkey}")
//    private String prodAppSecret;
//    private static String accessToken;
//
//    /**
//     * 3초에 한 번씩 주식의 거래량 정보들을 받아오기, 받은 값들은 redis cash에 저장
//     */
//    @Scheduled(cron = "*/3 * * * * ?")
//    public void getTradingVolumesSaveRedis() {
//
//        if (accessToken == null) {
//            accessToken = getAccessToken();
//        }
//
//        Mono<Object> mono = getVolumeRank(accessToken);
//
//        mono.flatMap(data -> {
//            ObjectMapper objectMapper = new ObjectMapper();
//            try {
//                String dataJson = objectMapper.writeValueAsString(data);
//                return Mono.just(dataJson);
//            } catch (JsonProcessingException e) {
//                log.error("데이터 JSON 변환 오류", e);
//                return Mono.empty();
//            }
//        }).subscribe(
//                dataJson -> {
//                    try {
//                        JSONObject reformattedJson = jsonReformat(dataJson);
//                        ValueOperations<String, String> vop = redisTemplate.opsForValue();
//                        vop.set("tradingVolumes", reformattedJson.toJSONString());
//                    } catch (ParseException e) {
//                        throw new RuntimeException(e);
//                    }
//                },
//                error -> {
//                    log.error("데이터 가져오기 오류", error);
//                }
//        );
//    }
//
//    /**
//     * controller가 호출할 때마다 redis cash에 저장된 값을 가져오기
//     */
//    public HomeDto.volumeReseponseDto getTradingFromRedis() throws JsonProcessingException {
//
//        ValueOperations<String, String> vop = redisTemplate.opsForValue();
//        String tradingVolumes = vop.get("tradingVolumes");
//
//        //mapper로 변환
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        return objectMapper.readValue(tradingVolumes, HomeDto.volumeReseponseDto.class);
//    }
//
//    public JSONObject jsonReformat(String dataJson) throws ParseException {
//
//        JSONParser parser = new JSONParser();
//        JSONObject originalJsonObject = (JSONObject) parser.parse(dataJson);
//        JSONArray outputArray = (JSONArray) originalJsonObject.get("output");
//
//        JSONArray dataVolumes = new JSONArray();
//        JSONObject newJsonObject = new JSONObject();
//
//        for (Object item : outputArray) {
//            JSONObject outputItem = (JSONObject) item;
//            JSONObject priceDataItem = new JSONObject();
//
//            priceDataItem.put("stock_name", outputItem.get("hts_kor_isnm"));
//            priceDataItem.put("data_rank", outputItem.get("data_rank"));
//            priceDataItem.put("stock_present_price", outputItem.get("stck_prpr"));
//            priceDataItem.put("stock_dod_percentage", outputItem.get("prdy_ctrt"));
//            priceDataItem.put("volumeIncreaseRate", outputItem.get("vol_inrt"));
//
//            dataVolumes.add(priceDataItem);
//        }
//        newJsonObject.put("trading_volumes", dataVolumes);
//
//        return newJsonObject;
//    }
//
//    public Mono<Object> getVolumeRank(String accessToken) {
//
//        return webClient.get()
//                .uri(uriBuilder -> uriBuilder
//                        .path("/uapi/domestic-stock/v1/quotations/volume-rank")
//                        .queryParam("FID_COND_MRKT_DIV_CODE", "J")
//                        .queryParam("FID_COND_SCR_DIV_CODE", "20171")
//                        .queryParam("FID_INPUT_ISCD", "0002")
//                        .queryParam("FID_DIV_CLS_CODE", "0")
//                        .queryParam("FID_BLNG_CLS_CODE", "1")
//                        .queryParam("FID_TRGT_CLS_CODE", "111111111")
//                        .queryParam("FID_TRGT_EXLS_CLS_CODE", "000000")
//                        .queryParam("FID_INPUT_PRICE_1", "0")
//                        .queryParam("FID_INPUT_PRICE_2", "0")
//                        .queryParam("FID_VOL_CNT", "20")
//                        .queryParam("FID_INPUT_DATE_1", "0")
//                        .build())
//                .header(HttpHeaders.CONTENT_TYPE, "application/json")
//                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)  // 올바른 헤더 이름을 사용
//                .header("appkey", prodAppKey)  // 다른 사용자 지정 헤더
//                .header("appsecret", prodAppSecret)  // 다른 사용자 지정 헤더
//                .header("tr_id", "FHPST01710000")
//                .retrieve()
//                .bodyToMono(Object.class);
//    }
//
//
//    @Scheduled(cron = "0 0 8 * * ?")
//    public void getToken() {
//        try {
//            accessToken = getAccessToken();
//        } catch (Exception e) {
//            log.error("accessTokenError", e);
//            throw new RuntimeException("Can't get accessToken");
//        }
//    }
//
//    public String getAccessToken() {
//        try {
//            TokenResponse tokenResponse = webClient.post()
//                    .uri("/oauth2/tokenP")
//                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                    .bodyValue("{\"grant_type\":\"client_credentials\",\"appkey\":\"" + prodAppKey + "\",\"appsecret\":\"" + prodAppSecret + "\"}")
//                    .retrieve()
//                    .bodyToMono(TokenResponse.class)
//                    .block();
//
//            if (tokenResponse != null) {
//                return tokenResponse.getAccess_token();
//            } else {
//                log.error("Failed to retrieve access token: TokenResponse is null");
//                return null;
//            }
//        } catch (Exception e) {
//            log.error("Failed to retrieve access token: " + e.getMessage(), e);
//            return null;
//        }
//    }