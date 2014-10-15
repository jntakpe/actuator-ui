package main.java.com.github.jntakpe.exception;

import org.springframework.http.HttpStatus;

/**
 * Gestion des codes d'erreurs fonctionnels
 *
 * @author jntakpe
 */
public enum FunctionnalCode implements ExceptionCode {

    UNIQUE_CONSTRAINT_VIOLATION(HttpStatus.CONFLICT);

    private final HttpStatus httpStatus;

    FunctionnalCode(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
