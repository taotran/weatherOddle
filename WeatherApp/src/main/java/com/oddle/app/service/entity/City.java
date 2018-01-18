package com.oddle.app.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by tao.tran on 12/16/17.
 */
@Entity
@Table(name = "city")
public class City extends AbstractEntity implements Serializable {

    @Column(name = "cityName")
    private String cityName;

    @Column(name = "country")
    private String country;

    @OneToMany(mappedBy = "city")
    private Set<WeatherLog> weatherLogs = new HashSet<>();

    public City() {
    }

    public City(String cityName, String country) {
        this.cityName = cityName;
        this.country = country;
    }

    public Set<WeatherLog> getWeatherLogs() {
        return weatherLogs;
    }

    public void setWeatherLogs(Set<WeatherLog> weatherLogs) {
        this.weatherLogs = weatherLogs;
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
}
