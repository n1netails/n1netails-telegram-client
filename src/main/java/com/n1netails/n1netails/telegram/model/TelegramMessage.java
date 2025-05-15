package com.n1netails.n1netails.telegram.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TelegramMessage {

    private String text;
    private boolean disableNotification;

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
}
