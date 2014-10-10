package com.github.jntakpe.service;

import com.github.jntakpe.repository.MetricsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Services associés à l'entité {@link com.github.jntakpe.domain.Metrics}
 *
 * @author jntakpe
 */
@Service
public class MetricsService {

    private final MetricsRepository metricsRepository;

    @Autowired
    public MetricsService(MetricsRepository metricsRepository) {
        this.metricsRepository = metricsRepository;
    }


}
