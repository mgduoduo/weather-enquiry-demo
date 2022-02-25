package com.sample.exception;

/**
 * @description:
 * @author: openzerol
 * @date: 2022年02月25日22:28:39
 **/
public class WeatherException extends RuntimeException {
    protected String desc;
    protected String code;

    public WeatherException() {
    }

    public WeatherException(String message, Throwable cause) {
        super(message, cause);
    }

    public WeatherException(Throwable cause) {
        super(cause);
    }

    public WeatherException(String message) {
        super(message);
    }

    public String getDesc() {
        return desc;
    }

    public String getCode() {
        return this.code;
    }

}