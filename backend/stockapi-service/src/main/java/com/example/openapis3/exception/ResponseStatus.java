package com.example.openapis3.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


@Getter
@RequiredArgsConstructor
public enum ResponseStatus {

    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 JWT입니다."), //401
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "만료된 JWT입니다."), //401
    NOT_FOUND_TOKEN(HttpStatus.UNAUTHORIZED, "존재하지 않는 JWT입니다."); //401

    private final HttpStatus httpStatus;
    private final String message;
}
