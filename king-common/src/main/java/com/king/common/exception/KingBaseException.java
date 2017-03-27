package com.king.common.exception;

/**
 * 异常基类
 * 2017.03.27
 */
public class KingBaseException extends RuntimeException {

    private Integer errorCode;

    private String errorMessage;


    public KingBaseException(Integer errorCode, String errorMessage) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}