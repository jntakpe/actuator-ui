package com.github.jntakpe.util;

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
     * @return le message final avec les variables du messages remplacées
     */
    public String getMessage(String codeMessage, Object... args) {
        return messageSource.getMessage(codeMessage, args, Locale.FRANCE);
    }
}
