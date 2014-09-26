package com.github.jntakpe.dto;

/**
 * Wrapper permettant de contrôleur la contrainte d'unicité d'un champ
 *
 * @author jntakpe
 */
public final class Unicity {

    private final String field;

    private final String id;

    private final Object value;

    public Unicity(String field, String id, Object value) {
        this.field = field;
        this.id = id;
        this.value = value;
    }

    public String getField() {
        return field;
    }

    public String getId() {
        return id;
    }

    public Object getValue() {
        return value;
    }

}
