package com.example.legendfive.overall.Service;

import com.example.legendfive.overall.dto.HomeDto;
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

    @Value("${openapi.appkey}")
    private String prodAppKey;
    @Value("${openapi.appsecretkey}")
    private String prodAppSecret;

    private String accessToken;

    @Scheduled(cron = "*/3 * * * * ?")
    public void getTradingVolumes() {

        getToken();
        Object block = stockApiRequest(accessToken).block();
        System.out.println(block);
    }

    @Scheduled(cron = "* * 8 * * ?")// Scheduled to run every day at 7:00 AM
    @PostConstruct
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

    /**
     * 주식 코드를 보내서 주식의 거래량 정보들을 받아오기
     */
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
                .header("appkey", "PSCjadYrwNhF5oaDn8A4g9Gof1o0qBvo6V5M")  // 다른 사용자 지정 헤더
                .header("appsecret", "ptqAmWD9Kk+9SEQ2ZEF0acHmJOVETOOoHyL3JMVM59/h316Hbxt87OHCl85ym9XEw9G/jbEbmrqrEaynutSpHfMzWoEPtRxys/l4SouokdDemOaJvBQLhzyLZunmW7cX7n9JyvlcneFE4XPFX/OV0oE7CrM3G7U6P6G24SwuLBsvGQxGw7M=")  // 다른 사용자 지정 헤더
                .header("tr_id", "FHPST01710000")
                .retrieve()
                .bodyToMono(Object.class);

    }
}
