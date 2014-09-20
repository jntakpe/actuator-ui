package com.github.jntakpe.config;

import com.github.jntakpe.util.Profile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Classe permettant de lancer et de configurer l'application
 *
 * @author jntakpe
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan("com.github.jntakpe")
@EnableMongoRepositories(basePackages = "com.github.jntakpe.repository")
public class ActuatorUiConfig extends SpringBootServletInitializer {

    private static Logger LOG = LoggerFactory.getLogger(ActuatorUiConfig.class);

    private static final String ACTIVE_PROFILE = "spring.profiles.active";

    /**
     * Démarre l'application en mode 'embedded'
     *
     * @param args arguments passés par le goal maven
     */
    public static void main(String[] args) {
        LOG.debug("Démarrage de l'application en mode 'embedded'");
        new SpringApplication(ActuatorUiConfig.class).run(args);
    }

    /**
     * Démarrage sur un serveur classique (Tomcat, Jetty, JBoss, etc ...)
     *
     * @param application builder de la configuration Spring Boot
     * @return la configuration prête à être lancée
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        LOG.debug("Démarrage de l'application en mode 'classique'");
        Profile profile = resolveProfile();
        LOG.debug("Profil '{}' sélectionné", profile.getConstant());
        application.profiles(profile.getConstant());
        return application.sources(ActuatorUiConfig.class);
    }

    /**
     * Ajoute le profil Spring à la configuration. Si aucun profil n'est spécifié alors le profil de développement est choisi
     *
     * @return profil de l'application
     */
    private Profile resolveProfile() {
        String profile = System.getProperty(ACTIVE_PROFILE);
        if (profile == null) {
            LOG.debug("Aucun profil spécifié. Profil par défaut sélectionné");
            return Profile.DEVELOPPEMENT;
        }
        return Profile.valueOf(profile.toUpperCase());
    }
}
