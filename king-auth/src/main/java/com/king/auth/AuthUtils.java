package com.king.auth;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthUtils {

    // token cookie的名字
    private static final String AUTH_TOKEN_NAME = "king-token";

    // 会话有效期
    private static final Integer SESSION_TIMEOUT = 30 * 1000 * 1000;


    /**
     * 添加cookie
     * 用于登陆成功时
     * @param response
     */
    public static void create(HttpServletResponse response) {
        String cookieValue = "";
        Cookie cookie = CookieUtils.createCookie(AUTH_TOKEN_NAME, cookieValue);
        response.addCookie(cookie);
    }


    /**
     * 验证会话是否存在以及是否有效
     * 用于每次访问API时
     * @param request
     * @return
     */
    public static boolean validate(HttpServletRequest request) {
        Cookie cookie = CookieUtils.getCookie(request, AUTH_TOKEN_NAME);
        if (cookie == null) {
            return false;
        }
        String cookieValue = cookie.getValue();
        // 对cookieValue进行解密
        AuthToken token = toAuthToken(cookieValue);
        if (token.getTimestamp() + SESSION_TIMEOUT < System.currentTimeMillis()) {
            return false;
        }
        return true;
    }


    /**
     * 刷新token
     * 用于正常访问API之后
     */
    public static void refreshToken() {

    }


    // 加密
    private static String encrypt(AuthToken token) {
        // 先将AuthToken对象转换为字符串
        String original = token.toString();
        // 再对转换后的字符串进行加密
        String password = AES.encrypt(original);
        return password;
    }


    // 解密为对象
    private static AuthToken toAuthToken(String password) {
        // 先对password进行解密
        String original = AES.decrypt(password);
        // 再转换为AuthToken对象
        AuthToken token = null;
        return token;
    }

}