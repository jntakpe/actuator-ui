package main.java.com.github.jntakpe.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.UUID;

/**
 * Classe unique de gestion des exceptions
 *
 * @author jOSS
 */
public class AuiException extends RuntimeException {

    private static final String SEPARATOR = "|";

    private final ExceptionCode code;

    private final String uniqueId;

    private final Object[] messageAttrs;

    private final Map<String, Object> params = new HashMap<>();

    /**
     * Constructeur
     *
     * @param code         code de l'exception {@link com.github.jntakpe.exception.FunctionnalCode} ou {@link com.github.jntakpe.exception.TechnicalCode}
     * @param uniqueId     identifiant unique de l'exception. Concaténation du nom de l'exception, date et id.
     * @param messageAttrs attributs du message d'erreur
     */
    private AuiException(ExceptionCode code, String uniqueId, Object... messageAttrs) {
        this.code = code;
        this.uniqueId = uniqueId;
        this.messageAttrs = messageAttrs;
    }

    /**
     * Constructeur
     *
     * @param code         code de l'exception {@link com.github.jntakpe.exception.FunctionnalCode} ou {@link com.github.jntakpe.exception.TechnicalCode}
     * @param uniqueId     identifiant unique de l'exception. Concaténation du nom de l'exception, date et id.
     * @param cause        cause de l'exception
     * @param messageAttrs attributs du message d'erreur
     */
    private AuiException(ExceptionCode code, String uniqueId, Throwable cause, Object... messageAttrs) {
        super(cause);
        this.code = code;
        this.uniqueId = uniqueId;
        this.messageAttrs = messageAttrs;
    }

    /**
     * Constructeur
     *
     * @param code         code de l'exception {@link com.github.jntakpe.exception.FunctionnalCode} ou {@link com.github.jntakpe.exception.TechnicalCode}
     * @param uniqueId     identifiant unique de l'exception. Concaténation du nom de l'exception, date et id.
     * @param message      message de l'exception
     * @param cause        cause de l'exception
     * @param messageAttrs attributs du message d'erreur
     */
    private AuiException(ExceptionCode code, String uniqueId, String message, Throwable cause, Object... messageAttrs) {
        super(message, cause);
        this.code = code;
        this.uniqueId = uniqueId;
        this.messageAttrs = messageAttrs;
    }

    /**
     * Créé une instance de {@link com.github.jntakpe.exception.AuiException}
     *
     * @param code         identifiant de l'exception
     * @param messageAttrs attributs du message d'erreur
     * @return AuiException
     */
    public static AuiException createInstance(ExceptionCode code, Object... messageAttrs) {
        String name = findExceptionCodeName(code);
        String exceptionUuid = generateUUID(name);
        return new AuiException(code, exceptionUuid, messageAttrs);
    }

    /**
     * Crée une instance de {@link com.github.jntakpe.exception.AuiException}
     *
     * @param throwable    exception initiale
     * @param code         identifiant de l'exception
     * @param messageAttrs attributs du message d'erreur
     * @return AuiException wrappant l'exception originale
     */
    public static AuiException wrapInstance(Throwable throwable, ExceptionCode code, Object... messageAttrs) {
        if (throwable instanceof AuiException) {
            AuiException auiException = (AuiException) throwable;
            if (code == auiException.getCode()) {
                return auiException;
            }
        }
        String name = findExceptionCodeName(code);
        String exceptionUuid = generateUUID(name);
        return new AuiException(code, exceptionUuid, throwable, messageAttrs);
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

    /**
     * Récupère une propriété
     *
     * @param paramKey clé de la propriété
     * @param <T>      type de la valeur de la propriété
     * @return la valeur de la propriété
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String paramKey) {
        return (T) params.get(paramKey);
    }

    /**
     * Ajoute une propriété à l'instance courante
     *
     * @param paramKey clé de la propriété
     * @param value    valeur de la propriété
     * @return instance courante
     */
    public AuiException set(String paramKey, Object value) {
        params.put(paramKey, value);
        return this;
    }

    public ExceptionCode getCode() {
        return code;
    }

    public String getUniqueId() {
        return uniqueId;
    }


    public Object[] getMessageAttrs() {
        return messageAttrs;
    }

    public Map<String, Object> getParams() {
        return params;
    }
}
