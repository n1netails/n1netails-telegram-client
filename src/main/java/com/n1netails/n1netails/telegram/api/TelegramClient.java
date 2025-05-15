package com.n1netails.n1netails.telegram.api;

import com.n1netails.n1netails.telegram.exception.TelegramClientException;
import com.n1netails.n1netails.telegram.model.TelegramMessage;

/**
 * Telegram Client
 * @author shahid foy
 */
public interface TelegramClient {

    /**
     * Send telegram message
     * @param chatId telegram chat id
     * @param botToken telegram bot token
     * @param message message
     * @throws TelegramClientException telegram client exception
     */
    void sendMessage(String chatId, String botToken, TelegramMessage message) throws TelegramClientException;
}
