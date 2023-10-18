package com.example.openapis3.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Exception extends RuntimeException {
    private final ResponseStatus errorResponseStatus;

    public Exception(ResponseStatus errorResponseStatus, String message) {
        super(message);
        this.errorResponseStatus = errorResponseStatus;
    }
}