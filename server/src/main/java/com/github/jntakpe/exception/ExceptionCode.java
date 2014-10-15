package main.java.com.github.jntakpe.exception;

import org.springframework.http.HttpStatus;

/**
 * Interface commune des codes d'exceptions
 *
 * @author jntakpe
 * @see com.github.jntakpe.exception.FunctionnalCode
 * @see com.github.jntakpe.exception.ExceptionCode
 */
public interface ExceptionCode {

    /**
     * Retourne le code HTTP
     *
     * @return code HTTP
     */
    HttpStatus getHttpStatus();

}
