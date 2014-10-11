package com.github.jntakpe.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Bean représentant diverses informations récupérable depuis le endpoint 'info' de Spring Boot Actuator
 *
 * @author jntakpe
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class InfoEndpoint {

    private String version;

    private Map<String, Object> other = new HashMap<>();

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Object get(String name) {
        return other.get(name);
    }

    @JsonAnyGetter
    public Map<String, Object> any() {
        return other;
    }

    @JsonAnySetter
    public void setOther(Map<String, Object> other) {
        this.other = other;
    }
}
