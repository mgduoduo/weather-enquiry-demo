package com.sample.util;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @description:
 * @author: openzerol
 * @date: 2022年02月25日23:40:18
 **/
public class RateLimiterUtil {
    public static final int NUM = 10;
    public static final RateLimiter rateLimiter = RateLimiter.create(NUM);

    /**
     * 尝试获取令牌
     */
    public static boolean tryAcquire() {
        return rateLimiter.tryAcquire();
    }
}