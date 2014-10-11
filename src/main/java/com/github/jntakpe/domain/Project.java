package com.github.jntakpe.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Bean représentant un projet monitoré
 *
 * @author jntakpe
 */
@Document
public class Project extends MongoEntity {

    @NotEmpty
    @Size(min = 3)
    private String name;

    private String version;

    @URL
    private String url;

    @NotNull
    private Integer interval;

    private boolean active;

    private UpdateRate updateRate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public UpdateRate getUpdateRate() {
        return updateRate;
    }

    public void setUpdateRate(UpdateRate updateRate) {
        this.updateRate = updateRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        return !(name != null ? !name.equals(project.name) : project.name != null);

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("url", url)
                .append("name", name)
                .toString();
    }

}
