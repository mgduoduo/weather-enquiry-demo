
package com.sample.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: openzerol
 * @date: 2022年02月24日22:20:40
 **/
public class OkHttpClientUtil {
    private static final int HTTP_RETRY_TIMES = 2;

    private static OkHttpClient client;

    public static String get(URL url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = getClientInstance().newCall(request).execute();
        if (response.body() != null) {
            return response.body().string();
        }
        return null;
    }

    private static OkHttpClient getClientInstance() {
        if (client == null) {
            synchronized (OkHttpClientUtil.class) {
                if (client == null) {
                    client = new OkHttpClient().newBuilder()
                            .connectTimeout(5, TimeUnit.SECONDS)
                            .writeTimeout(10, TimeUnit.SECONDS)
                            .readTimeout(10, TimeUnit.SECONDS)
                            .addInterceptor(new OkHttpInterceptor(HTTP_RETRY_TIMES))
                            .build();
                }
            }
        }
        return client;
    }
}