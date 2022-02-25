/*
 *Copyright: 2016 www.chebada.com Inc. All rights reserved.
 * 注意：本内容仅限于车巴达公司内部传阅，禁止外泄以及用于其他的商业目的
 *
 *@Project: weather-search
 *@File: WeatherInfo
 *@Package: com.sample.domain
 *@Date: 2022年02月24日
 *@Author:openz
 *
 */
package com.sample.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @description:
 * @author: openz@chebada.com
 * @date: 2022年02月24日23:31:08
 **/
public class WeatherInfo {
    @JsonProperty("weatherinfo")
    private WeatherinfoDTO weatherInfo;

    public static class WeatherinfoDTO {
        /**
         * city : 苏州
         * cityid : 101190401
         * temp : 23.9
         * WD : 东北风
         * WS : 小于3级
         * SD : 79%
         * AP : 1004.9hPa
         * njd : 暂无实况
         * WSE : <3
         * time : 18:00
         * sm : 1.5
         * isRadar : 0
         * Radar :
         */

        @JsonProperty("city")
        private String city;
        @JsonProperty("cityid")
        private String cityId;
        @JsonProperty("temp")
        private Double temp;
        @JsonProperty("WD")
        private String wd;
        @JsonProperty("WS")
        private String ws;
        @JsonProperty("SD")
        private String sd;
        @JsonProperty("AP")
        private String ap;
        @JsonProperty("njd")
        private String njd;
        @JsonProperty("WSE")
        private String wse;
        @JsonProperty("time")
        private String time;
        @JsonProperty("sm")
        private String sm;
        @JsonProperty("isRadar")
        private String isRadar;
        @JsonProperty("Radar")
        private String radar;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }

        public Double getTemp() {
            return temp;
        }

        public void setTemp(Double temp) {
            this.temp = temp;
        }

        public String getWd() {
            return wd;
        }

        public void setWd(String wd) {
            this.wd = wd;
        }

        public String getWs() {
            return ws;
        }

        public void setWs(String ws) {
            this.ws = ws;
        }

        public String getSd() {
            return sd;
        }

        public void setSd(String sd) {
            this.sd = sd;
        }

        public String getAp() {
            return ap;
        }

        public void setAp(String ap) {
            this.ap = ap;
        }

        public String getNjd() {
            return njd;
        }

        public void setNjd(String njd) {
            this.njd = njd;
        }

        public String getWse() {
            return wse;
        }

        public void setWse(String wse) {
            this.wse = wse;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getSm() {
            return sm;
        }

        public void setSm(String sm) {
            this.sm = sm;
        }

        public String getIsRadar() {
            return isRadar;
        }

        public void setIsRadar(String isRadar) {
            this.isRadar = isRadar;
        }

        public String getRadar() {
            return radar;
        }

        public void setRadar(String radar) {
            this.radar = radar;
        }
    }

    public WeatherinfoDTO getWeatherInfo() {
        return weatherInfo;
    }

    public void setWeatherInfo(WeatherinfoDTO weatherInfo) {
        this.weatherInfo = weatherInfo;
    }
}