package com.king.auth;

import com.king.common.utils.AES;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登陆认证工具类
 * 2016.12.26
 */
public class AuthUtils {

    @Autowired
    private static CookieUtils cookieUtils;

    @Autowired
    private static CookieSettings cookieSettings;


    /**
     * 添加cookie
     * 用于登陆成功时
     */
    public void addAuthTokenCookie(HttpServletResponse response, String userId) {
        // 创建AuthToken对象
        AuthToken token = new AuthToken(userId, System.currentTimeMillis());
        // 将AuthToken对象转换为字符串
        String plainText = token.toString();
        // 对转换后的字符串进行加密
        String cipherText = AES.encrypt(plainText, cookieSettings.getSecretKey());
        // 创建cookie
        Cookie cookie = cookieUtils.createCookie(cookieSettings.getAuthTokenName(), cipherText);
        // 将cookie添加到response对象中返回给客户端
        response.addCookie(cookie);
    }


    /**
     * 验证会话是否存在以及是否过期
     * 用于每次访问API时
     */
    public static boolean validate(HttpServletRequest request) {
        Cookie cookie = cookieUtils.getCookie(request, cookieSettings.getAuthTokenName());
        if (cookie == null) {
            throw new UnauthorizedException("尚未登录!");
        }
        // 对cookie value进行解密
        String plainText = AES.decrypt(cookie.getValue(), cookieSettings.getSecretKey());
        // 将解密后的字符串转换为AuthToken对象
        AuthToken token = AuthToken.parse(plainText);
        // 判断token是否过期
        boolean isTokenEffective = token.getTimestamp() + cookieSettings.getTimeout() < System.currentTimeMillis();
        if (isTokenEffective) {
            // 刷新token
            return true;
        } else {
            throw new UnauthorizedException("会话已过期!");
        }
    }


    /**
     * 刷新token
     * 用于正常访问API之后
     * 为了避免频繁刷新token......
     */
    public static void refreshToken(AuthToken token) {
        token.setTimestamp(System.currentTimeMillis());
        // 对新的token进行加密
        String cipherText = "";
        // 覆盖已有的token

    }

}