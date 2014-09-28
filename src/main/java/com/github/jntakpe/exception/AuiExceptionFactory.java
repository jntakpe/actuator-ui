package com.github.jntakpe.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;
import java.util.UUID;

/**
 * Fabrique permettant de créer des instances de {@link com.github.jntakpe.exception.AuiException}
 *
 * @author jntakpe
 */
public final class AuiExceptionFactory {

    private static final String SEPARATOR = "|";

    /**
     * Créé une instance de {@link com.github.jntakpe.exception.AuiException}
     *
     * @param code identifiant de l'exception
     * @return AuiException
     */
    public static AuiException createInstance(ExceptionCode code) {
        String name = findExceptionCodeName(code);
        String exceptionUuid = generateUUID(name);
        return new AuiException(code, exceptionUuid);
    }

    /**
     * Crée une instance de {@link com.github.jntakpe.exception.AuiException}
     *
     * @param throwable exception initiale
     * @param code      identifiant de l'exception
     * @return AuiException wrappant l'exception originale
     */
    public static AuiException wrapInstance(Throwable throwable, ExceptionCode code) {
        if (throwable instanceof AuiException) {
            AuiException auiException = (AuiException) throwable;
            if (code == auiException.getCode()) {
                return auiException;
            }
        }
        String name = findExceptionCodeName(code);
        String exceptionUuid = generateUUID(name);
        return new AuiException(code, exceptionUuid, throwable);
    }

    private static String findExceptionCodeName(ExceptionCode code) {
        String name;
        if (code instanceof FunctionnalCode) {
            name = ((FunctionnalCode) code).name();
        } else {
            name = ((TechnicalCode) code).name();
        }
        return name;
    }


    private static String generateUUID(String name) {
        String time = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return new StringJoiner(SEPARATOR).add(name).add(time).add(uuid).toString();
    }
}
