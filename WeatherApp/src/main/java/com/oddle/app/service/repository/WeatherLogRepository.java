package com.oddle.app.service.repository;

import com.oddle.app.service.entity.WeatherLog;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by tao.tran on 12/17/17.
 */
public interface WeatherLogRepository extends EntityRepository<WeatherLog> {

    @Query(value = "select w from WeatherLog w group by w.city.cityName, w.id")
    List<WeatherLog> findAllGroupByCityName();
}
