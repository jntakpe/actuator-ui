package com.github.jntakpe.dto;

/**
 * Objet définissant un message renvoyé à une vue pour définir le statut d'une opération
 *
 * @author jntakpe
 */
public final class MessageResponse {

    private final boolean success;

    private final String message;

    private MessageResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public static MessageResponse success() {
        return new MessageResponse(true, null);
    }

    public static MessageResponse success(String message) {
        return new MessageResponse(true, message);
    }

    public static MessageResponse error(String message) {
        return new MessageResponse(false, message);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "MessageResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}
