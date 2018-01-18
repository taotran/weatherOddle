package com.oddle.app.service.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by tao.tran on 12/15/17.
 * <p>
 * WeatherLog entity is built base on Builder pattern
 */

@Entity
@Table(name = "weatherLog")
public class WeatherLog extends AbstractEntity implements Serializable {

    @NotNull
    @Column(name = "weatherDate")
    private Date weatherDate;

    @NotNull
    @Column(name = "temp")
    private double temperature;

    @Column(name = "main")
    private String weatherMain;

    @Column(name = "description")
    private String weatherDescription;

    @Min(value = 0)
    @Column(name = "windspeed")
    private double windSpeed;

    @Column(name = "humidity")
    private double humidity;

    @Column(name = "icon")
    private String icon;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "fk_city")
    private City city;

    @SuppressWarnings("all")
    public WeatherLog() {

    }

    private WeatherLog(Builder builder) {
        this.weatherDate = builder.weatherDate;
        this.temperature = builder.temperature;
        this.city = builder.city;
        this.weatherMain = builder.weatherMain;
        this.weatherDescription = builder.weatherDescription;
        this.windSpeed = builder.windSpeed;
        this.humidity = builder.humidity;
        this.icon = builder.icon;
    }

    public Date getWeatherDate() {
        return weatherDate;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getWeatherMain() {
        return weatherMain;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public double getHumidity() {
        return humidity;
    }

    public String getIcon() {
        return icon;
    }

    public City getCity() {
        return city;
    }

    public static final class Builder {

        private Date weatherDate;
        private double temperature;
        private City city;

        private String weatherMain;
        private String weatherDescription;
        private double windSpeed;
        private double humidity;
        private String icon;


        public Builder(Date weatherDate, double temperature, City city) {
            this.weatherDate = weatherDate;
            this.temperature = temperature;
            this.city = city;
        }

        public Builder weatherMain(String value) {
            this.weatherMain = value;
            return this;
        }

        public Builder weatherDescription(String value) {
            this.weatherDescription = value;
            return this;
        }

        public Builder windSpeed(double value) {
            this.windSpeed = value;
            return this;
        }

        public Builder humidity(double value) {
            this.humidity = value;
            return this;
        }

        public Builder icon(String value) {
            this.icon = value;
            return this;
        }

        @NotNull
        public WeatherLog build() {
            return new WeatherLog(this);
        }

    }
}
