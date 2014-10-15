package main.java.com.github.jntakpe.util;

import com.github.jntakpe.exception.FunctionnalCode;
import com.github.jntakpe.exception.TechnicalCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Encapsulation de la classe {@link org.springframework.context.MessageSource}
 *
 * @author jntakpe
 */
@Component
public class MessageManager {

    @Autowired
    private MessageSource messageSource;

    /**
     * Récupère un message dans les bundles de l'application et remplace les variables du message
     *
     * @param codeMessage code du message
     * @param args        variables du message
     * @return le message final avec les variables du message remplacées
     */
    public String getMessage(String codeMessage, Object... args) {
        return messageSource.getMessage(codeMessage, args, Locale.getDefault());
    }

    /**
     * Récupère un message dans les bundles de l'application et remplace les variables du message
     *
     * @param code code d'une exception fonctionnelle
     * @param args variables du message
     * @return le message final avec les variables du message remplacées
     */
    public String getMessage(FunctionnalCode code, Object... args) {
        return messageSource.getMessage(code.name(), args, Locale.getDefault());
    }

    /**
     * Récupère un message dans les bundles de l'application et remplace les variables du message
     *
     * @param code code d'une exception technique
     * @param args variables du message
     * @return le message final avec les variables du message remplacées
     */
    public String getMessage(TechnicalCode code, Object... args) {
        return messageSource.getMessage(code.name(), args, Locale.getDefault());
    }
}
