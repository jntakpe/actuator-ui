package com.github.jntakpe.domain;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * Classe représentant une entité abstraite MongoDB.
 *
 * @author jntakpe
 */
public abstract class MongoEntity implements Serializable {

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
