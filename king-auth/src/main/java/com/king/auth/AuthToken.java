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


    public AuthToken(String userId, Long timestamp) {
        this.userId = userId;
        this.timestamp = timestamp;
    }


    // 是否有必要只保留get方法

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


    /**
     * 将token字符串转换为AuthToken对象
     */
    public static AuthToken parse(String tokenStr) {
        String[] str = tokenStr.split(",");
        if (str.length != 2) {
            throw new UnauthorizedException("token解析错误");
        }
        String userId = str[0];
        Long timestamp = Long.parseLong(str[1]);
        return new AuthToken(userId, timestamp);
    }

}