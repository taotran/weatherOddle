package com.oddle.app.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oddle.app.service.WeatherLogService;
import com.oddle.app.service.entity.WeatherLog;
import com.oddle.app.service.entity.ajax.request.SearchCriteria;
import com.oddle.app.service.entity.open.weather.OpenWeatherRecord;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import springfox.documentation.spring.web.json.Json;

import java.io.IOException;
import java.util.List;

/**
 * Author   : tao.tran
 * Date     : 12/15/17
 */
@RestController
@RequestMapping("/api")
public class WeatherRest {

    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper;

    private final WeatherLogService weatherLogService;

    //ID: 210dd7faafb2b8447d2e6cd87249bebb
    private static final String APP_ID = "210dd7faafb2b8447d2e6cd87249bebb";

    private static final String WEATHER_BY_NAME = "https://api.openweathermap.org/data/2.5/weather?q=%s&APPID=%s";

    public WeatherRest(@Qualifier("restTemplate") RestTemplate restTemplate, ObjectMapper objectMapper, WeatherLogService weatherLogService) {
        Assert.notNull(restTemplate, "'restTemplate' must not be null!");
        Assert.notNull(objectMapper, "'objectMapper' must not be null!");
        Assert.notNull(weatherLogService, "'weatherLogService' must not be null!");

        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.weatherLogService = weatherLogService;
    }

    @PostMapping(path = "/search")
    @ResponseStatus(HttpStatus.CREATED)
    public void search(@RequestBody SearchCriteria searchCriteria) throws IOException {
        Assert.notNull(searchCriteria, "'searchCriteria' must not be null!");

        final String requestUrl = String.format(WEATHER_BY_NAME, searchCriteria.getCityName(), APP_ID);
        OpenWeatherRecord weatherRecord = restTemplate.getForObject(requestUrl, OpenWeatherRecord.class);

        try {
            weatherRecord = restTemplate.getForObject(requestUrl, OpenWeatherRecord.class);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() != HttpStatus.NOT_FOUND)
                throw e;
        }

        weatherLogService.saveOpenWeatherRecord(weatherRecord);
    }

    @GetMapping(path = "/showAll")
    public List<WeatherLog> showAll() {
        return weatherLogService.findAllGroupByCityName();
    }
}
