package com.oddle.app.exception;

/**
 * Created by tao.tran on 12/17/17.
 */
public class OpenWeatherRecordNotFoundException extends RuntimeException {

    public OpenWeatherRecordNotFoundException() {
        this("OpenWeatherRecord is invalid!");
    }

    public OpenWeatherRecordNotFoundException(String message) {
        super(message);
    }
}
