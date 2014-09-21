package com.github.jntakpe.web;

import com.github.jntakpe.util.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Aspects transversaux aux contrôleurs
 *
 * @author jntakpe
 */
@ControllerAdvice
public class ControllerAdviser {

    private final Environment environment;

    @Autowired
    public ControllerAdviser(Environment environment) {
        this.environment = environment;
    }

    /**
     * Indique aux vues si l'application est sur un profil de développement
     *
     * @return true si mode de développement
     */
    @ModelAttribute("isDevMode")
    public boolean isDevMode() {
        return Profile.valueOf(environment.getActiveProfiles()[0].toUpperCase()).isDevProfile();
    }
}
