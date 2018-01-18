package com.oddle.app.service;

import com.oddle.app.service.entity.AbstractEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by tao.tran on 12/14/17.
 */
public interface EntityService<T extends AbstractEntity> {

    List<T> findAll();

    List<T> findAll(Pageable pageable);

    T findOne(Long id);

    T save(T t);

    void delete(T t);

}