package com.oddle.app.service.repository;

import com.oddle.app.service.entity.City;

/**
 * Created by tao.tran on 12/17/17.
 */
public interface CityRepository extends EntityRepository<City> {

    City findByCityName(String cityName);
}
