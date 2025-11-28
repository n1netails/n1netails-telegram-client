package com.n1netails.n1netails.telegram.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Telegram Message
 * @author shahid foy
 */
@Getter
@Setter
public class TelegramMessage {

    private String text;
    private boolean disableNotification;
    private InlineKeyboardMarkup replyMarkup;

    /**
     * Telegram Message Constructor
     */
    public TelegramMessage() {}

    /**
     * Telegram Message Constructor
     * @param text telegram content
     * @param disableNotification disable telegram notification
     */
    public TelegramMessage(String text, boolean disableNotification) {
        this.text = text;
        this.disableNotification = disableNotification;
    }

    /**
     * Telegram Message Constructor
     * @param text telegram content
     * @param disableNotification disable telegram notification
     * @param replyMarkup the reply markup
     */
    public TelegramMessage(String text, boolean disableNotification, InlineKeyboardMarkup replyMarkup) {
        this.text = text;
        this.disableNotification = disableNotification;
        this.replyMarkup = replyMarkup;
    }
}
