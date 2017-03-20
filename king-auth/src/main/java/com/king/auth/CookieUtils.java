package com.king.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cookie工具类
 * 缺点：utils类中的方法通常为static的
 * 2016.12.26
 */
@Component
public class CookieUtils {

    @Autowired
    private CookieSettings cookieSettings;


    /**
     * 根据cookie名，从request中获取对应的cookie
     */
    public Cookie getCookie(HttpServletRequest request, String cookieName) {
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
     */
    public Cookie createCookie(String cookieName, String cookieValue) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setDomain(cookieSettings.getDomain());
        cookie.setMaxAge(cookieSettings.getTimeout());
        cookie.setHttpOnly(true);
        return cookie;
    }


    /**
     * 据cookie名，删除cookie
     */
    public void deleteCookie(HttpServletRequest request, HttpServletResponse response, String cookieName) {
        Cookie cookie = getCookie(request, cookieName);
        if (cookie != null) {
            cookie.setMaxAge(-1);
            response.addCookie(cookie);
        }
    }

}