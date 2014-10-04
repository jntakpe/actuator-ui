package com.github.jntakpe.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Paramètres relatifs à l'internationalisation des messages
 *
 * @author jntakpe
 */
@Configuration
@ConfigurationProperties(prefix="i18n")
public class LocalizationProperties {

    private Integer cache;

    private String encoding;

    private String prefix;

    private List<String> names = new ArrayList<>();

    public Integer getCache() {
        return cache;
    }

    public void setCache(Integer cache) {
        this.cache = cache;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }
}
