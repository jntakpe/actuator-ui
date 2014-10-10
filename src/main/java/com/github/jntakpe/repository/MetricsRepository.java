package com.github.jntakpe.repository;

import com.github.jntakpe.domain.Metrics;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Publication des méthodes d'accès aux données de l'entité {@link com.github.jntakpe.domain.Metrics}
 *
 * @author jntakpe
 */
public interface MetricsRepository extends MongoRepository<Metrics, String> {

}
