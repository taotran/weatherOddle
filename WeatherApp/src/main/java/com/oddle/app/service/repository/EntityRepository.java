package com.oddle.app.service.repository;

import com.oddle.app.service.entity.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by tao.tran on 12/14/17.
 */
public interface EntityRepository<T extends AbstractEntity> extends JpaRepository<T, Long> {
}
