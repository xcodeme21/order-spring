package com.example.utils;

public class TimeUtil {
    public static long expiredTime() {
        return System.currentTimeMillis() + (1000L * 60 * 60 * 24);
    }
}
