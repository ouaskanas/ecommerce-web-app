package com.ouaskanas.commerce.config;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
public class CookiesConfig {
    @Value("${spring.application.jwt.cookies.cookieName}")
    private String cookieName;
    @Value("${spring.application.jwt.cookies.expiration}")
    private long expiration;
    @Value("${spring.application.jwt.cookies.secure}")
    private boolean secure;

    public void CreateCookies(HttpServletResponse response, String token) {
        ResponseCookie cookie = ResponseCookie.from(cookieName, token)
                                                .secure(secure)
                                                .httpOnly(true)
                                                .path("/")
                                                .maxAge(expiration)
                                                .build() ;
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }

    public void ClearCookies(HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie.from(cookieName, "")
                                                .httpOnly(true)
                                                .path("/")
                                                .maxAge(0)
                                                .secure(secure)
                                                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }

    public String ExtractCookie(HttpServletRequest request) {
        if(request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if(cookie.getName().equals(cookieName)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

}