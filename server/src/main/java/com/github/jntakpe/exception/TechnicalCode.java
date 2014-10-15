package main.java.com.github.jntakpe.exception;

import org.springframework.http.HttpStatus;

/**
 * Gestion des codes d'erreurs techniques
 *
 * @author jntakpe
 */
public enum TechnicalCode implements ExceptionCode {

    ;

    private final HttpStatus httpStatus;

    TechnicalCode(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
