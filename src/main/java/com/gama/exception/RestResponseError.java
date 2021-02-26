package com.gama.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestResponseError {

    private String error;

    public static RestResponseError fromMessageDuplicate(String message) {
        RestResponseError resp = new RestResponseError();
        resp.error = message;
        return resp;
    }

    public static RestResponseError userNotFoundException(String message) {
        RestResponseError resp = new RestResponseError();
        resp.error = message;
        return resp;
    }
}