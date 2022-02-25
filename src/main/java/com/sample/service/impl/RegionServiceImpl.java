package com.sample.service.impl;

import com.sample.constant.WeatherConstant;
import com.sample.service.RegionService;
import com.sample.util.JacksonUtil;
import com.sample.util.OkHttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Map;

/**
 * @description:
 * @author: openzerol
 * @date: 2022年02月24日22:45:44
 **/
public class RegionServiceImpl implements RegionService {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public Map<String, String> getProvinces() {
        try {
            String message = OkHttpClientUtil.get(new URL(WeatherConstant.URL_GET_PROVINCE));
            log.info("province list:{}", message);
            return JacksonUtil.json2Map(message, String.class, String.class);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public Map<String, String> getCities(String provinceCode) {
        try {
            String message = OkHttpClientUtil.get(new URL(MessageFormat.format(WeatherConstant.URL_GET_CITY_BY_PROVINCE, provinceCode)));
            log.info("province:{}. city list:{}", provinceCode, message);
            return JacksonUtil.json2Map(message, String.class, String.class);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public Map<String, String> getCounties(String provinceCode, String cityCode) {
        try {
            String message = OkHttpClientUtil.get(new URL(MessageFormat.format(WeatherConstant.URL_GET_COUNTY_BY_CITY, provinceCode + cityCode)));
            log.info("province:{}, city:{}. county list:{}", provinceCode, cityCode, message);
            return JacksonUtil.json2Map(message, String.class, String.class);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}