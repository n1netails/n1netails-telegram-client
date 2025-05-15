package com.n1netails.n1netails.telegram.exception;

/**
 * Telegram Client Exception
 * @author shahid foy
 */
public class TelegramClientException extends Exception {

    /**
     * Telegram Client Exception Constructor
     * @param message exception message
     */
    public TelegramClientException(String message) { super(message); }

    /**
     * Telegram Client Exception Constructor
     * @param message exception message
     * @param cause cause
     */
    public TelegramClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
