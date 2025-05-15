package com.n1netails.n1netails.telegram.internal;

import com.n1netails.n1netails.telegram.api.TelegramClient;
import com.n1netails.n1netails.telegram.exception.TelegramClientException;
import com.n1netails.n1netails.telegram.model.TelegramMessage;
import com.n1netails.n1netails.telegram.service.BotService;

public class TelegramClientImpl implements TelegramClient {

    private final BotService botService;

    public TelegramClientImpl(BotService botService) { this.botService = botService; }

    @Override
    public void sendMessage(String chatId, String botToken, TelegramMessage message) throws TelegramClientException {
        try {
            botService.send(chatId, botToken, message);
        } catch (Exception e) {
            throw new TelegramClientException("Failed to send Telegram message", e);
        }
    }
}
