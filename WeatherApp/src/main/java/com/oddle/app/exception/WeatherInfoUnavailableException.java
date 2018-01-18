package com.oddle.app.exception;

/**
 * Created by tao.tran on 12/17/17.
 */
public class WeatherInfoUnavailableException extends RuntimeException {
    public WeatherInfoUnavailableException() {
        this("Weather information is unavailable!");
    }

    public WeatherInfoUnavailableException(String message) {
        super(message);
    }
}
