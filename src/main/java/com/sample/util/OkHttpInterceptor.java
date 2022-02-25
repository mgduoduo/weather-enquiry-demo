/*
 *Copyright: 2016 www.chebada.com Inc. All rights reserved.
 * 注意：本内容仅限于车巴达公司内部传阅，禁止外泄以及用于其他的商业目的
 *
 *@Project: weather-search
 *@File: OkHttpInterceptor
 *@Package: com.sample.util
 *@Date: 2022年02月25日
 *@Author:openz
 *
 */
package com.sample.util;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * @description:
 * @author: openz@chebada.com
 * @date: 2022年02月25日23:21:15
 **/
public class OkHttpInterceptor implements Interceptor {
    private int maxRetryTimes;

    public OkHttpInterceptor(int maxRetryTimes) {
        this.maxRetryTimes = maxRetryTimes;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        return retry(chain, 0);
    }

    /**
     * retry if any exception
     *
     * @param chain
     * @param retryTimes
     * @return
     */
    private Response retry(Chain chain, int retryTimes) {
        Request request = chain.request();
        Response response = null;
        try {
            response = chain.proceed(request);
        } catch (Exception e) {
            if (maxRetryTimes > retryTimes) {
                return retry(chain, retryTimes + 1);
            }
        } finally {
            return response;
        }
    }
}