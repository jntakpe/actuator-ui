package com.github.jntakpe.service;

import org.springframework.web.client.RestTemplate;

/**
 * Interface définissant les connecteurs aux différents endpoints de Spring Actuator
 *
 * @author jntakpe
 */
public interface ActuatorService<T> {

    /**
     * Renvoi un {@link org.springframework.web.client.RestTemplate}
     *
     * @return {@link org.springframework.web.client.RestTemplate}
     */
    default RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    /**
     * Récupère les données d'un endpoint REST
     *
     * @param url url du endpoint REST
     * @return l'objet modélisant le endpoint
     */
    T data(String url);

}
