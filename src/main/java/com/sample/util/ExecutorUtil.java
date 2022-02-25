package com.sample.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: openzerol
 * @date: 2022年02月24日22:53:03
 **/
public class ExecutorUtil {
    private static final int DEFAULT_POOL_CAPACITY = 2 << 4;

    public static final ExecutorService taskExecutor = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors(),
            Runtime.getRuntime().availableProcessors() * 2,
            30,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(DEFAULT_POOL_CAPACITY),
            new ThreadPoolExecutor.CallerRunsPolicy());

}