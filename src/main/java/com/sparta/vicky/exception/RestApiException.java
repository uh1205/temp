package com.sparta.vicky.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RestApiException {

    private int statusCode;
    private String msg;

}