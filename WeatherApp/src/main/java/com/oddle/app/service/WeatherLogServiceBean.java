package com.oddle.app.service;

import com.oddle.app.exception.WeatherInfoUnavailableException;
import com.oddle.app.service.entity.City;
import com.oddle.app.service.entity.WeatherLog;
import com.oddle.app.service.entity.open.weather.OpenWeatherRecord;
import com.oddle.app.service.entity.open.weather.Weather;
import com.oddle.app.service.repository.WeatherLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * Created by tao.tran on 12/17/17.
 */
@Service
public class WeatherLogServiceBean extends EntityServiceBean<WeatherLog> implements WeatherLogService {

    private static final Logger logger = LoggerFactory.getLogger(WeatherLogServiceBean.class);

    private final CityService cityService;

    private final WeatherLogRepository weatherLogRepository;

    @Autowired
    public WeatherLogServiceBean(CityService cityService, WeatherLogRepository weatherLogRepository) {

        Assert.notNull(cityService, "'cityService' must not be null!");
        Assert.notNull(weatherLogRepository, "'weatherLogRepository' must not be null!");
        this.cityService = cityService;
        this.weatherLogRepository = weatherLogRepository;
    }

    @Transactional
    @Override
    public WeatherLog saveOpenWeatherRecord(OpenWeatherRecord owr) {

        if (!isValidOpenWeatherRecord(owr))
            return null;

        final City city = getCity(owr.getSys().getCountry(), owr.getName());

        final Weather owrWeather = owr.getWeather().get(0);

        final WeatherLog weatherLog =
                new WeatherLog.Builder(new Date(owr.getDt()), owr.getMain().getTemp(), city)
                        .weatherMain(owrWeather.getMain())
                        .weatherDescription(owrWeather.getDescription())
                        .humidity(owr.getMain().getHumidity())
                        .icon(owrWeather.getIcon())
                        .windSpeed(owr.getWind().getSpeed())
                        .clouds(owr.getClouds().getAll())
                        .coordLon(owr.getCoord().getLon())
                        .coordLat(owr.getCoord().getLat())
                        .build();


        logger.info("### weatherLog is being persisted...");

        return super.save(weatherLog);
    }

    @Override
    public List<WeatherLog> findAllGroupByCityName() {
        return weatherLogRepository.findAllGroupByCityName();
    }

    private boolean isValidOpenWeatherRecord(OpenWeatherRecord owr) {

        if (owr == null) {
            return false;
        }

        if (owr.getWeather().isEmpty()) {
            throw new WeatherInfoUnavailableException();
        }

        return true;
    }

    private City getCity(String country, String cityName) {
        return cityService.findOrCreate(cityName, country);
    }
}
