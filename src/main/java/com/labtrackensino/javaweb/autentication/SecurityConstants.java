package com.labtrackensino.javaweb.autentication;

public class SecurityConstants {

    public static final String SECRET = "p6HKuoNvZGjOLi1utwgwSmBxPD_xpEkNf8jI9xZ4YORbWAgIkzsUl0WyIK_M0Z41wjudv0DjlSHSrHjjbBPgxg";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String CONTENT_TYPE = "application/json";

}
