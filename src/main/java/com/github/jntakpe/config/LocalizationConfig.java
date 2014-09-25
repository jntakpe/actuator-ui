package com.github.jntakpe.config;

import com.github.jntakpe.config.properties.LocalizationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.jntakpe.util.Profile.Constant.DEVELOPPEMENT;
import static com.github.jntakpe.util.Profile.Constant.EMBEDDED;

/**
 * Configuration de l'internationalisation des messages
 *
 * @author jntakpe
 */
@Configuration
public class LocalizationConfig {

    private static final Logger LOG = LoggerFactory.getLogger(LocalizationConfig.class);

    @Autowired
    private LocalizationProperties localizationProperties;

    @Autowired
    private Environment environment;

    /**
     * Configuration d'une source de messages actualisées toutes les 10 secondes dans les environnements de développement
     *
     * @return la source de messages
     */
    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames(resolveBundlePaths());
        if (environment.acceptsProfiles(EMBEDDED, DEVELOPPEMENT)) {
            messageSource.setCacheSeconds(localizationProperties.getCache());
        }
        messageSource.setDefaultEncoding(localizationProperties.getEncoding());
        LOG.trace("Internationalisation des messages configurée");
        return messageSource;
    }

    private String[] resolveBundlePaths() {
        String prefix = localizationProperties.getPrefix();
        List<String> bundlePaths = localizationProperties.getNames().stream().map(name -> prefix + name).collect(Collectors.toList());
        String[] fileNames = new String[bundlePaths.size()];
        if (!bundlePaths.isEmpty()) {
            fileNames = bundlePaths.toArray(fileNames);
            LOG.trace("Chargement des bundles de messages {}", Arrays.toString(fileNames));
        } else {
            LOG.trace("Aucun bundle de message configuré");
        }
        return fileNames;
    }

}
