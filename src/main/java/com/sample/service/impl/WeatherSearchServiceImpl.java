package com.sample.service.impl;

import com.google.common.util.concurrent.RateLimiter;
import com.sample.constant.WeatherConstant;
import com.sample.domain.WeatherInfo;
import com.sample.service.RegionService;
import com.sample.service.WeatherService;
import com.sample.util.JacksonUtil;
import com.sample.util.OkHttpClientUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.util.Asserts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: openzerol
 * @date: 2022年02月24日22:37:17
 **/
public class WeatherSearchServiceImpl implements WeatherService {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    public static final int MAX_TPS = 100;

    public RateLimiter rateLimiter;

    private final RegionService regionService;

    public WeatherSearchServiceImpl() {
        regionService = new RegionServiceImpl();
        rateLimiter = RateLimiter.create(MAX_TPS);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Double> getAccurateTemperature(String province, String city, String county) {
        if (!tryAcquire()) {
            log.warn("Request is out of limit");
            return Optional.empty();
        }
        validArgument(province, city, county);
        try {
            String url = MessageFormat.format(WeatherConstant.URL_GET_WEATHER, StringUtils.join(province, city, county));
            String result = OkHttpClientUtil.get(new URL(url));
            WeatherInfo weatherInfo = JacksonUtil.json2Bean(result, WeatherInfo.class);
            if (weatherInfo != null
                    && weatherInfo.getWeatherInfo() != null
                    && weatherInfo.getWeatherInfo().getTemp() != null) {
                log.info("region:{}, temperature:{}", StringUtils.join(province, city, county), weatherInfo.getWeatherInfo().getTemp());
                return Optional.of(weatherInfo.getWeatherInfo().getTemp());
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return Optional.empty();
    }

    private void validArgument(String province, String city, String county) {
        Asserts.check(StringUtils.isNotBlank(province), "[province] can't be empty!");
        Asserts.check(StringUtils.isNotBlank(city), "[city] can't be empty!");
        Asserts.check(StringUtils.isNotBlank(county), "[county] can't be empty!");

        Map<String, String> provinces = regionService.getProvinces();
        Asserts.check(provinces.containsKey(province), "[province code:" + province + "] is invalid!");
        Map<String, String> cities = regionService.getCities(province);
        Asserts.check(cities.containsKey(city), "[city code:" + city + "] is invalid city in [province:" + province + "]!");
        Map<String, String> counties = regionService.getCounties(province, city);
        Asserts.check(counties.containsKey(county), "[county code:" + county + "] is invalid county in [province:" + province + "][city:" + city + "]!");
    }

    /**
     * try to get the token
     */
    public boolean tryAcquire() {
        return rateLimiter.tryAcquire();
    }
}