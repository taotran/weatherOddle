package com.oddle.app.service;

import com.oddle.app.exception.EntityExistException;
import com.oddle.app.exception.InvalidEntityIdException;
import com.oddle.app.service.entity.AbstractEntity;
import com.oddle.app.service.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by tao.tran on 12/14/17.
 */

@Service
public class EntityServiceBean<T extends AbstractEntity> implements EntityService<T> {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private EntityRepository<T> entityRepository;

    @Override
    public List<T> findAll() {
        return entityRepository.findAll();
    }

    @Override
    public List<T> findAll(Pageable pageable) {
        return entityRepository.findAll(pageable).getContent();
    }

    @Override
    public T findOne(Long id) {
        return entityRepository.findOne(id);
    }

    @Override
    @Transactional
    public T save(@Valid T t) {

        if (findOne(t.getId()) != null) {
            throw new EntityExistException();
        }

        em.persist(t);

        em.flush();
        return t;
    }

    @Override
    @Transactional
    public void delete(T t) {
        entityRepository.delete(t);
    }
}
