package com.king.auth;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Cookie相关配置类
 * 2016.12.26
 */
@Component
@ConfigurationProperties(prefix = "king.cookie")
public class CookieSettings {

    private String authTokenName;
    private Integer timeout;
    private String domain;
    // 对cookie进行AES加解密的密钥
    private String secretKey;


    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getAuthTokenName() {
        return authTokenName;
    }

    public void setAuthTokenName(String authTokenName) {
        this.authTokenName = authTokenName;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

}