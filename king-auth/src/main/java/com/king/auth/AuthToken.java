package com.king.auth;

/**
 * AuthToken
 * 2016.12.26
 */
public class AuthToken {

    // 用户id
    private String userId;

    // token创建时的时间戳
    private Long timestamp;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }


    @Override
    public String toString() {
        return userId + "," + timestamp;
    }

}