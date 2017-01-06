package com.king.auth;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * cookie工具类
 */
public class CookieUtils {

    /**
     * 根据cookie名，从request中获取对应的cookie
     * @param request
     * @param cookieName
     * @return
     */
    public static Cookie getCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                return cookie;
            }
        }
        return null;
    }


    /**
     * 创建cookie
     * @param cookieName
     * @param cookieValue
     * @return
     */
    public static Cookie createCookie(String cookieName, String cookieValue) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setHttpOnly(true);
        return cookie;
    }


    /**
     * 删除cookie
     * @param cookieName
     */
    public void deleteCookie(String cookieName) {

    }

}