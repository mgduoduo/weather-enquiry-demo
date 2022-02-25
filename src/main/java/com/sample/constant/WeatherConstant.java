/*
 *Copyright: 2016 www.chebada.com Inc. All rights reserved.
 * 注意：本内容仅限于车巴达公司内部传阅，禁止外泄以及用于其他的商业目的
 *
 *@Project: weather-search
 *@File: WeatherConstant
 *@Package: com.sample.constant
 *@Date: 2022年02月24日
 *@Author:openz
 *
 */
package com.sample.constant;

/**
 * @description:
 * @author: openz@chebada.com
 * @date: 2022年02月24日23:43:09
 **/
public class WeatherConstant {
    public static final String URL_GET_PROVINCE = "http://www.weather.com.cn/data/city3jdata/china.html";
    public static final String URL_GET_CITY_BY_PROVINCE = "http://www.weather.com.cn/data/city3jdata/provshi/{0}.html";
    public static final String URL_GET_COUNTY_BY_CITY = "http://www.weather.com.cn/data/city3jdata/station/{0}.html";
    public static final String URL_GET_WEATHER = "http://www.weather.com.cn/data/sk/{0}.html";
}