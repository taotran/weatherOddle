package com.oddle.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oddle.app.service.WeatherLogService;
import com.oddle.app.service.entity.WeatherLog;
import com.oddle.app.service.entity.open.weather.OpenWeatherRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Author   : tao.tran
 * Date     : 12/17/17
 */
@Controller
public class WeatherController {

    //ID: 210dd7faafb2b8447d2e6cd87249bebb
    private static final String APP_ID = "210dd7faafb2b8447d2e6cd87249bebb";
    private static final String WEATHER_BY_NAME = "https://api.openweathermap.org/data/2.5/weather?q=%s&APPID=%s";
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final WeatherLogService weatherLogService;

    @Autowired
    public WeatherController(@Qualifier("restTemplate") RestTemplate restTemplate, ObjectMapper objectMapper, WeatherLogService weatherLogService) {
        Assert.notNull(restTemplate, "'restTemplate' must not be null!");
        Assert.notNull(objectMapper, "'objectMapper' must not be null!");
        Assert.notNull(weatherLogService, "'weatherLogService' must not be null!");

        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.weatherLogService = weatherLogService;
    }

    @GetMapping({"/home", "/"})
    public String home() {
        return "home";
    }

//    @PostMapping("/home/search")
//    public String search(String cityName, Model model) throws HttpClientErrorException {
//        Assert.notNull(cityName, "'cityName' must not be null!");
//
//        final String requestUrl = String.format(WEATHER_BY_NAME, cityName, APP_ID);
//        OpenWeatherRecord weatherRecord = null;
//
//        try {
//            weatherRecord = restTemplate.getForObject(requestUrl, OpenWeatherRecord.class);
//        } catch (HttpClientErrorException e) {
//            if (e.getStatusCode() != HttpStatus.NOT_FOUND)
//                throw e;
//        }
//
//        final WeatherLog weatherLog = weatherLogService.saveOpenWeatherRecord(weatherRecord);
//
//
//        model.addAttribute("weatherLog", weatherLog);
//        return "home";
//    }

    @GetMapping("/home/search/{cityName}")
    public String search1(@PathVariable String cityName, Model model) throws HttpClientErrorException {
        Assert.notNull(cityName, "'cityName' must not be null!");

        final String requestUrl = String.format(WEATHER_BY_NAME, cityName, APP_ID);
        OpenWeatherRecord weatherRecord = null;

        try {
            weatherRecord = restTemplate.getForObject(requestUrl, OpenWeatherRecord.class);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() != HttpStatus.NOT_FOUND)
                throw e;
        }

        final WeatherLog weatherLog = weatherLogService.saveOpenWeatherRecord(weatherRecord);

        model.addAttribute("weatherLog", weatherLog);
        return "result/search_result_fragment :: resultFragment";
    }

    @GetMapping("/home/showAll")
    public String showAll(Model model) {

        final List<WeatherLog> allWeatherLogs = weatherLogService.findAllGroupByCityName();

        model.addAttribute("allWls", allWeatherLogs);

        return "result/show_all_weather_fragment :: allWeatherFragment";
    }

    @GetMapping("/home/delete/{weatherId}")
    public String delete(@PathVariable long weatherId, Model model) {

        weatherLogService.delete(weatherId);

        final List<WeatherLog> allWeatherLogs = weatherLogService.findAllGroupByCityName();

        model.addAttribute("allWls", allWeatherLogs);

        return "result/show_all_weather_fragment :: allWeatherFragment";
    }

}
