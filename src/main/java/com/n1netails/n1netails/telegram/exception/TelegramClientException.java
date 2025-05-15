package com.n1netails.n1netails.telegram.exception;

public class TelegramClientException extends Exception {

    public TelegramClientException(String message) { super(message); }

    public TelegramClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
