package com.king.auth;

/**
 * 未授权异常
 * 2016.12.26
 */
public class UnauthorizedException extends RuntimeException {

    private static Integer errorCode = 401001;
    private String errorMesage;

    public UnauthorizedException(Integer errorCode, String errorMesage) {
        super();
        this.errorCode = errorCode;
        this.errorMesage = errorMesage;
    }

    public UnauthorizedException(String errorMesage) {
        this(errorCode, errorMesage);
    }

}