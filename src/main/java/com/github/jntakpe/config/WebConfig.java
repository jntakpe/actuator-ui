package com.github.jntakpe.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

/**
 * Configuration du contexte web
 *
 * @author jntakpe
 */
@Configuration
public class WebConfig {

    public static final String DEV_MODE_ATTR = "isDevMode";

    @Autowired
    private ServletContext servletContext;

    @PostConstruct
    public void initWebContext() {
        servletContext.setAttribute(DEV_MODE_ATTR, ActuatorUiConfig.getCurrentProfile().isDevProfile());
    }
}
