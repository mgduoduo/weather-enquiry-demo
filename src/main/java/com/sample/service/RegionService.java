package com.sample.service;

import java.util.Map;

/**
 * @description:
 * @author: openzerol
 * @date: 2022年02月24日22:41:56
 **/
public interface RegionService {
    /**
     * get provinces
     *
     * @return
     */
    Map<String, String> getProvinces();

    /**
     * get cities by province code
     *
     * @param provinceCode
     * @return
     */
    Map<String, String> getCities(String provinceCode);

    /**
     * get counties by city code
     *
     * @param provinceCode
     * @param cityCode
     * @return
     */
    Map<String, String> getCounties(String provinceCode, String cityCode);
}