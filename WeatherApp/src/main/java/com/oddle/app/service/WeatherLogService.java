package com.oddle.app.service;

import com.oddle.app.service.entity.WeatherLog;
import com.oddle.app.service.entity.open.weather.OpenWeatherRecord;

import java.util.List;

/**
 * Created by tao.tran on 12/17/17.
 */
public interface WeatherLogService extends EntityService<WeatherLog> {

    WeatherLog saveOpenWeatherRecord(OpenWeatherRecord openWeatherRecord);

    List<WeatherLog> findAllGroupByCityName();
}
