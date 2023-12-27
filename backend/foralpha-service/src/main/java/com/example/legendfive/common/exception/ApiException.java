package com.example.legendfive.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
@RequiredArgsConstructor
public class ApiException extends RuntimeException {
    private final ApiResponseStatus apiResponseStatus;
    public ApiException(ApiResponseStatus ApiResponseStatus, String message) {
        super(message);
        this.apiResponseStatus = ApiResponseStatus;
    }
}