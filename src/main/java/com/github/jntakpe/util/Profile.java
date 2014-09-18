package com.github.jntakpe.util;

/**
 * Énumération des différents profils de l'application
 *
 * @author jntakpe
 */
public enum Profile {

    EMBEDDED(Constant.EMBEDDED),
    DEVELOPPEMENT(Constant.DEVELOPPEMENT),
    PRODUCTION(Constant.PRODUCTION);

    private final String constant;

    Profile(String constant) {
        this.constant = constant;
    }

    public String getConstant() {
        return constant;
    }

    public static class Constant {

        public static final String EMBEDDED = "embedded";

        public static final String DEVELOPPEMENT = "developpement";

        public static final String PRODUCTION = "production";

        private Constant() {
        }

    }
}
