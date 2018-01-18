package com.oddle.app.service.entity.ajax.response;

import com.oddle.app.service.entity.WeatherLog;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Author   : tao.tran
 * Date     : 12/18/17
 */
public class CityWeatherLog {

    private String cityName;

    private String country;

    private Date weatherDate;

    private double temperature;

    private String weatherMain;

    private String weatherDescription;

    private double windSpeed;

    private double humidity;

    private String icon;

    public CityWeatherLog(WeatherLog weatherLog) {
        this.cityName = weatherLog.getCity().getCityName();
        this.country = weatherLog.getCity().getCountry();
        this.weatherDate = weatherLog.getWeatherDate();
        this.temperature = weatherLog.getTemperature();
        this.weatherMain = weatherLog.getWeatherMain();
        this.weatherDescription = weatherLog.getWeatherDescription();
        this.windSpeed = weatherLog.getWindSpeed();
        this.humidity = weatherLog.getHumidity();
        this.icon = weatherLog.getIcon();
    }

    public CityWeatherLog(String cityName, String country, Date weatherDate, double temperature, String weatherMain, String weatherDescription, double windSpeed, double humidity, String icon) {
        this.cityName = cityName;
        this.country = country;
        this.weatherDate = weatherDate;
        this.temperature = temperature;
        this.weatherMain = weatherMain;
        this.weatherDescription = weatherDescription;
        this.windSpeed = windSpeed;
        this.humidity = humidity;
        this.icon = icon;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getWeatherDate() {
        return weatherDate;
    }

    public void setWeatherDate(Date weatherDate) {
        this.weatherDate = weatherDate;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getWeatherMain() {
        return weatherMain;
    }

    public void setWeatherMain(String weatherMain) {
        this.weatherMain = weatherMain;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
