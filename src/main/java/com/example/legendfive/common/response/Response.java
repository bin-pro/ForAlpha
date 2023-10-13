package com.example.legendfive.common.response;

public class Response<T> {
    private final T data;

    public Response(T data) {
        this.data = data;
    }
    public T getData() {
        return data;
    }
    public static <T> Response<T> of(T data) {
        return new Response<>(data);
    }
}
