package com.github.jntakpe.service;

import com.github.jntakpe.domain.MongoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * Publication des méthodes Spring Data MongoDB par défaut
 *
 * @author jntakpe
 */
@NoRepositoryBean
public interface MongoService<T extends MongoEntity> extends MongoRepository<T, String> {

    /**
     * Méthode permettant d'injecter le repository MongoDB de Spring Data
     *
     * @return le repository injecté depuis la classe fille
     */
    MongoRepository<T, String> getMongoRepository();

    @Override
    default <S extends T> List<S> save(Iterable<S> entities) {
        return getMongoRepository().save(entities);
    }

    @Override
    default List<T> findAll() {
        return getMongoRepository().findAll();
    }

    @Override
    default List<T> findAll(Sort sort) {
        return getMongoRepository().findAll(sort);
    }

    @Override
    default Page<T> findAll(Pageable pageable) {
        return getMongoRepository().findAll(pageable);
    }

    @Override
    default <S extends T> S save(S entity) {
        return getMongoRepository().save(entity);
    }

    @Override
    default T findOne(String id) {
        return getMongoRepository().findOne(id);
    }

    @Override
    default boolean exists(String id) {
        return getMongoRepository().exists(id);
    }

    @Override
    default Iterable<T> findAll(Iterable<String> ids) {
        return getMongoRepository().findAll(ids);
    }

    @Override
    default long count() {
        return getMongoRepository().count();
    }

    @Override
    default void delete(String id) {
        getMongoRepository().delete(id);
    }

    @Override
    default void delete(T entity) {
        getMongoRepository().delete(entity);
    }

    @Override
    default void delete(Iterable<? extends T> entities) {
        getMongoRepository().delete(entities);
    }

    @Override
    default void deleteAll() {
        getMongoRepository().deleteAll();
    }

}
