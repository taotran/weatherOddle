package com.oddle.app.service;

import com.oddle.app.service.entity.City;

/**
 * Created by tao.tran on 12/17/17.
 */
public interface CityService extends EntityService<City> {

    City findByCityName(String cityName);

    City findOrCreate(String cityName, String country);
}
