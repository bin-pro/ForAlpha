package com.example.legendfive.overall.Service;

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

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
@Slf4j
public class HomeService {

    private final WebClient webClient;
    private final RedisTemplate<String, String> redisTemplate;

    @Value("${openapi.appkey}")
    private String prodAppKey;
    @Value("${openapi.appsecretkey}")
    private String prodAppSecret;
    private String accessToken;

    /**
     * 3초에 한 번씩 주식의 거래량 정보들을 받아오기, 받은 값들은 redis cash에 저장
     */
    @Scheduled(cron = "*/3 * * * * ?")
    public void getTradingVolumesSaveRedis() {
        getToken();
        Object block = stockApiRequest(accessToken).block();

        ValueOperations<String, String> vop = redisTemplate.opsForValue();

        System.out.println(block);
    }

    /**
     * controller가 호출할 때마다 redis cash에 저장된 값을 가져오기
     */
    public void getTraindVolumesGetRedis() {
        // redis cash에서 값을 가져오기
    }

    @Scheduled(cron = "0 0 8 * * ?")// Scheduled to run every day at 7:00 AM
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

    public Mono<Object> stockApiRequest(String token) {

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/uapi/domestic-stock/v1/quotations/volume-rank")
                        .queryParam("FID_COND_MRKT_DIV_CODE", "J")
                        .queryParam("FID_COND_SCR_DIV_CODE", "20171")
                        .queryParam("FID_INPUT_ISCD", "0002")
                        .queryParam("FID_DIV_CLS_CODE", "0")
                        .queryParam("FID_BLNG_CLS_CODE", "0")
                        .queryParam("FID_TRGT_CLS_CODE", "111111111")
                        .queryParam("FID_TRGT_EXLS_CLS_CODE", "000000")
                        .queryParam("FID_INPUT_PRICE_1", "0")
                        .queryParam("FID_INPUT_PRICE_2", "0")
                        .queryParam("FID_VOL_CNT", "20")
                        .queryParam("FID_INPUT_DATE_1", "0")
                        .build())
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)  // 올바른 헤더 이름을 사용
                .header("appkey", prodAppKey)  // 다른 사용자 지정 헤더
                .header("appsecret", prodAppSecret)  // 다른 사용자 지정 헤더
                .header("tr_id", "FHPST01710000")
                .retrieve()
                .bodyToMono(Object.class);
    }
}
