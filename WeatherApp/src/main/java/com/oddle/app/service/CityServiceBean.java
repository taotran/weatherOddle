package com.oddle.app.service;

import com.oddle.app.service.entity.City;
import com.oddle.app.service.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * Created by tao.tran on 12/17/17.
 */
@Service
public class CityServiceBean extends EntityServiceBean<City> implements CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityServiceBean(CityRepository cityRepository) {
        Assert.notNull(cityRepository, "'cityRepository' must not be null!");

        this.cityRepository = cityRepository;
    }

    @Override
    public City findByCityName(String cityName) {
        return cityRepository.findByCityName(cityName);
    }

    @Override
    public City findOrCreate(String cityName, String country) {

        final City cityByName = findByCityName(cityName);

        return cityByName == null ? save(new City(cityName, country)) : cityByName;
    }
}
