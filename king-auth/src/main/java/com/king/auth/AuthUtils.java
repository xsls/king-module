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


    // token cookie的名字
    private static final String AUTH_TOKEN_NAME = "king-token";

    // 会话有效期
    private static final Integer SESSION_TIMEOUT = 30 * 1000 * 1000;


    /**
     * 添加cookie
     * 用于登陆成功时
     * @param response
     */
    public void create(HttpServletResponse response) {
        String cookieValue = "";
        Cookie cookie = cookieUtils.createCookie(AUTH_TOKEN_NAME, cookieValue);
        response.addCookie(cookie);
    }


    /**
     * 验证会话是否存在以及是否有效
     * 用于每次访问API时
     * @param request
     * @return
     */
    public static boolean validate(HttpServletRequest request) {
        Cookie cookie = cookieUtils.getCookie(request, cookieSettings.getAuthTokenName());
        if (cookie == null) {
            throw new UnauthorizedException("登陆认证失败!");
        }
        String cookieValue = cookie.getValue();
        // 对cookieValue进行解密
        String plainText = "";
        AuthToken token = toAuthToken(cookieValue);
        boolean isSessionEffective = token.getTimestamp() + cookieSettings.getTimeout() < System.currentTimeMillis();
        if (isSessionEffective) {
            // 刷新会话
            return true;
        } else {
            throw new UnauthorizedException("会话已过期!");
        }
    }


    /**
     * 刷新token
     * 用于正常访问API之后
     */
    public static void refreshToken(AuthToken token) {
        token.setTimestamp(System.currentTimeMillis());
        // 对新的token进行加密
        String cipherText = "";
        // 覆盖已有的token

    }


    // 加密
    private static String encrypt(AuthToken token) {
        // 先将AuthToken对象转换为字符串
        String original = token.toString();
        // 再对转换后的字符串进行加密
        String password = AES.encrypt(original, "");
        return password;
    }


    // 解密为对象
    private static AuthToken toAuthToken(String password) {
        // 先对password进行解密
        String original = AES.decrypt(password, "");
        // 再转换为AuthToken对象
        AuthToken token = null;
        return token;
    }

}