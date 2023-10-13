//package com.example.legendfive.save.service;
//
//import com.example.legendfive.save.dto.AccessTokenResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.*;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.HttpClientErrorException;
//import org.springframework.web.client.HttpServerErrorException;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.util.UriComponentsBuilder;
//
//@Component
//public class StockApiCronJob {
//
//    @Value("${openapi.appkey}")
//    private String openApiKey;
//
//    @Value("${openapi.appsecretkey}")
//    private String openApiSecret;
//
//    private String accessToken;
//    private final RestTemplate restTemplate;
//
//    @Autowired
//    public StockApiCronJob(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
//
//    @Scheduled(cron = "0 0 8 * * ?") // Scheduled to run every day at 8:00 AM
//    public void refreshToken() {
//        // Define the URL for token request
//        String tokenUrl = "https://openapi.ebestsec.co.kr:8080/oauth2/token";
//
//        // Set request parameters using UriComponentsBuilder
//        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(tokenUrl)
//                .queryParam("grant_type", "client_credentials")
//                .queryParam("appkey", "PS5UDohI3ppyV3Tnhb3C8av2KxVpKpjg83Ga")
//                .queryParam("appsecretkey", "UehtW1vizaJlSmVNWvqNDyxbCy2Qr8u9")
//                .queryParam("scope", "oob");
//
//        // Create the request entity with headers
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//
//        try {
//            // Send the POST request to get the access token
//            ResponseEntity<AccessTokenResponse> response = restTemplate.exchange(
//                    builder.toUriString(),
//                    HttpMethod.POST,
//                    new HttpEntity<>(headers),
//                    AccessTokenResponse.class
//            );
//
//            if (response.getStatusCode() == HttpStatus.OK) {
//                AccessTokenResponse tokenResponse = response.getBody();
//                accessToken = tokenResponse.getAccess_token();
//            } else {
//                throw new Exception("Failed to obtain access token");
//            }
//        } catch (HttpClientErrorException | HttpServerErrorException e) {
//            // Handle HttpClientErrorException or HttpServerErrorException
//            e.printStackTrace();
//        } catch (Exception e) {
//            // Handle other exceptions
//            e.printStackTrace();
//        }
//    }
//
//    @Scheduled(cron = "0 0 7 * * ?") // Scheduled to run every day at 7:00 AM
//    public void performApiTask() {
//        if (accessToken != null) {
//            String apiUrl = "https://api.example.com/some-endpoint";
//            HttpHeaders headers = new HttpHeaders();
//            headers.set("Authorization", "Bearer " + accessToken);
//            // Perform API request and data processing logic here
//        } else {
//            System.out.println("Access token not available.");
//        }
//    }
//}
////모든 주식에 대한 50일자 값을 아침에 한번 가져오면 됨.
//// 그냥 차트나 검색을 볼 때 필요한거는 모든 개별 주식의 50일자 값에 한번에
//// 주식 예측하기 버튼을 누르면 주식 코드, 기간, 현재가 가져와 저장
////매일 아침에 스케줄러를 돌면서 주식 예측 하기 기록의 값과, 현재의 기록의 값을 저장
