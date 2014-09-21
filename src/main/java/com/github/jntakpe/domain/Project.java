package com.github.jntakpe.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Bean représentant un projet monitoré
 *
 * @author jntakpe
 */
public class Project extends MongoEntity {

    private String name;

    private String description;

    private String version;

    private String url;

    public Project(String name, String description, String version, String url) {
        this.name = name;
        this.description = description;
        this.version = version;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
