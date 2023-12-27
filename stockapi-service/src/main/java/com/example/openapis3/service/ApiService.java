package com.example.openapis3.service;

import com.example.openapis3.dto.TokenResponse;
import com.example.openapis3.util.AwsS3Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
@Slf4j
@RequiredArgsConstructor
public class ApiService {

    private final WebClient webClient;
    private final AwsS3Util awsS3Util;

    @Value("${openapi.prod_appkey}")
    private String prodAppKey;
    @Value("${openapi.prod_appsecret}")
    private String prodAppSecret;
    private static String accessToken;

    @Scheduled(cron = "0 0 8 * * ?")
    public void getToken() {
        accessToken = getAccessToken();
        if (accessToken != null) {
            log.info("Access Token: " + accessToken);
        }
    }

    @Scheduled(cron = "0 0 9 * * ?")
    public void ApiDataToS3() {

        if (accessToken == null) {
            accessToken = getAccessToken();
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(this.getClass().getResourceAsStream("/allStockTheme.csv")))) // CSV 파일 경로
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(","); // CSV 라인을 파싱

                String json = stockApiRequest(accessToken, data[0]).block();

                JSONObject reformattedJson = null;
                if (json != null) {
                    reformattedJson = jsonReformat(data[0], data[1], data[2], json);
//                    System.out.println(reformattedJson);
                }
                else{
                    System.out.println("json is null");
                }
                awsS3Util.dataToS3(data[0], reformattedJson);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    public JSONObject jsonReformat(String stockCode, String stockName, String themeName, String originalJson) throws ParseException {

        JSONParser parser = new JSONParser();
        JSONObject originalJsonObject = (JSONObject) parser.parse(originalJson);

        JSONObject output1 = (JSONObject) originalJsonObject.get("output1");

        JSONObject newJsonObject = new JSONObject();

        newJsonObject.put("stock_code", stockCode);
        newJsonObject.put("stock_name", stockName);
        newJsonObject.put("stock_theme_code", themeName);
        newJsonObject.put("stock_present_price", output1.get("stck_prpr"));
        newJsonObject.put("stock_dod_percentage", output1.get("prdy_ctrt"));

        JSONArray priceDataArray = new JSONArray();
        JSONArray output2Array = (JSONArray) originalJsonObject.get("output2");
        Integer maxPrice = 0, minPrice = Integer.MAX_VALUE;

        for (Object item : output2Array) {
            JSONObject output2Item = (JSONObject) item;
            JSONObject priceDataItem = new JSONObject();

            priceDataItem.put("start_price", output2Item.get("stck_oprc"));
            priceDataItem.put("last_price", output2Item.get("stck_clpr"));
            priceDataItem.put("max_price", output2Item.get("stck_hgpr"));
            priceDataItem.put("min_price", output2Item.get("stck_lwpr"));

            priceDataArray.add(priceDataItem);

            Integer currentMaxPrice = Integer.parseInt(output2Item.get("stck_hgpr").toString());
            Integer currentMinPrice = Integer.parseInt(output2Item.get("stck_lwpr").toString());

            maxPrice = Math.max(maxPrice, currentMaxPrice);
            minPrice = Math.min(minPrice, currentMinPrice);
        }

        newJsonObject.put("stock_max_30", maxPrice.toString());
        newJsonObject.put("stock_min_30", minPrice.toString());
        newJsonObject.put("price_data", priceDataArray);


        /***이평선 계산***/
        JSONArray maLineData = calculateMovingAverages(output2Array);
        newJsonObject.put("ma_line_data", maLineData);

        return newJsonObject;
    }

    public JSONArray calculateMovingAverages(JSONArray dailyData) {
        JSONArray maLineData = new JSONArray();

        for (int i = 0; i < dailyData.size(); i++) {
            double fiveDaySum = 0.0;
            double tenDaySum = 0.0;
            double twentyDaySum = 0.0;

            for (int j = i; j > i - 5; j--) {
                if (j >= 0) {
                    JSONObject dayData = (JSONObject) dailyData.get(j);
                    double closingPrice = Double.parseDouble(dayData.get("stck_clpr").toString());
                    fiveDaySum += closingPrice;
                }
            }

            for (int j = i; j > i - 10; j--) {
                if (j >= 0) {
                    JSONObject dayData = (JSONObject) dailyData.get(j);
                    double closingPrice = Double.parseDouble(dayData.get("stck_clpr").toString());
                    tenDaySum += closingPrice;
                }
            }

            for (int j = i; j > i - 20; j--) {
                if (j >= 0) {
                    JSONObject dayData = (JSONObject) dailyData.get(j);
                    double closingPrice = Double.parseDouble(dayData.get("stck_clpr").toString());
                    twentyDaySum += closingPrice;
                }
            }

            JSONObject maData = new JSONObject();
            maData.put("five_ma", Integer.toString((int) (fiveDaySum / 5)));
            maData.put("ten_ma",  Integer.toString((int) (tenDaySum / 10)));
            maData.put("twenty_ma",  Integer.toString((int) (twentyDaySum / 20)));
            maLineData.add(maData);
        }

        return maLineData;
    }

    /******************************************************************************/

    /**
     * 한투 open api로부터 access token을 받아오기
     */
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
     * 주식 코드를 보내서 주식의 정보들을 받아오기
     */
    public Mono<String> stockApiRequest(String token, String stockCode) {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(50);

        String formattedStartDate = startDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String formattedEndDate = endDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/uapi/domestic-stock/v1/quotations/inquire-daily-itemchartprice")
                        .queryParam("fid_cond_mrkt_div_code", "J")
                        .queryParam("fid_input_iscd", stockCode)
                        .queryParam("fid_input_date_1", formattedStartDate)
                        .queryParam("fid_input_date_2", formattedEndDate)
                        .queryParam("fid_period_div_code", "D")
                        .queryParam("fid_org_adj_prc", "1")
                        .build())
                .header(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .header("appkey", prodAppKey)
                .header("appsecret", prodAppSecret)
                .header("tr_id", "FHKST03010100")
                .retrieve()
                .bodyToMono(String.class);
    }
}
