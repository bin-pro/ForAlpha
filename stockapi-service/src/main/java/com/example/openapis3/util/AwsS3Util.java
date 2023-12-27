package com.example.openapis3.util;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class AwsS3Util {

    private final AmazonS3Client s3Client;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public void dataToS3(String stockCode, Object data) {

        String key = "todayStock/" + stockCode + ".json"; // S3에 저장될 파일 경로 및 이름
        // JSON 데이터를 문자열로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = objectMapper.writeValueAsString(data);

            byte[] contentBytes = jsonString.getBytes("UTF-8");

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(contentBytes.length);
            metadata.setContentType("application/json"); // JSON 형식으로 설정
            metadata.setContentEncoding("UTF-8"); // UTF-8 인코딩으로 설정

            ByteArrayInputStream inputStream = new ByteArrayInputStream(contentBytes);
            s3Client.putObject(new PutObjectRequest(bucket, key, inputStream, metadata));

            log.info("Data saved to S3 successfully: " + key);
        } catch (IOException e) {
            log.error("Failed to save data to S3: " + key, e);
        }
    }
}

