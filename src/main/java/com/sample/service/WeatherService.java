package com.sample.service;

import java.util.Optional;

/**
 * @description:
 * @author: openzerol
 * @date: 2022年02月24日22:33:28
 **/
public interface WeatherService {

    /**
     * get accurate temperature info
     *
     * @param province
     * @param city
     * @param county
     * @return
     */
    public Optional<Double> getAccurateTemperature(String province, String city, String county);
}