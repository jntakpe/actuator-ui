package com.github.jntakpe.service;

import com.github.jntakpe.domain.MongoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Service abstrait contenant les méthodes courantes de gestion des entités MongoDB
 *
 * @author jntakpe
 */
public abstract class MongoAbstractService<T extends MongoEntity> implements MongoRepository<T, String> {

    private MongoRepository<T, String> mongoRepository;

    public MongoAbstractService(MongoRepository<T, String> mongoRepository) {
        this.mongoRepository = mongoRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S extends T> List<S> save(Iterable<S> entities) {
        return mongoRepository.save(entities);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> findAll() {
        return mongoRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> findAll(Sort sort) {
        return mongoRepository.findAll(sort);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<T> findAll(Pageable pageable) {
        return mongoRepository.findAll(pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S extends T> S save(S entity) {
        return mongoRepository.save(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T findOne(String id) {
        return mongoRepository.findOne(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean exists(String id) {
        return mongoRepository.exists(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<T> findAll(Iterable<String> ids) {
        return mongoRepository.findAll(ids);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long count() {
        return mongoRepository.count();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(String id) {
        mongoRepository.delete(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(T entity) {
        mongoRepository.delete(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Iterable<? extends T> entities) {
        mongoRepository.delete(entities);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll() {
        mongoRepository.deleteAll();
    }
}
