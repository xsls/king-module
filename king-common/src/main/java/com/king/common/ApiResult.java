package com.king.common;

/**
 * API返回结果
 * 2017.03.27
 */
public class ApiResult<T> {

    private Integer errorCode;

    private String errorMessage;

    private T data;


    private ApiResult(Integer errorCode, String errorMessage, T data) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.data = data;
    }


    public ApiResult of(Integer errorCode, String errorMessage, T data) {
        return new ApiResult(errorCode, errorMessage, data);
    }


    public ApiResult of(T data) {
        return new ApiResult(200, "OK", data);
    }

}