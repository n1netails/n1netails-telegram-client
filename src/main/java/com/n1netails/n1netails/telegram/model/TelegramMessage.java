package com.n1netails.n1netails.telegram.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Telegram Message
 * @author shahid foy
 */
@Getter
@Setter
public class TelegramMessage {

    private String text;
    private String animation;
    private List<String> images;
    private List<String> videos;
    private boolean disableNotification;
    private InlineKeyboardMarkup replyMarkup;

    /**
     * Telegram Message Constructor
     */
    public TelegramMessage() {}

    /**
     * Telegram Message Constructor
     * @param text telegram content
     * @param animation animation url
     * @param disableNotification disable telegram notification
     */
    public TelegramMessage(String text, String animation, boolean disableNotification) {
        this.text = text;
        this.animation = animation;
        this.disableNotification = disableNotification;
    }

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

    /**
     * Telegram Message Constructor
     * @param text telegram content
     * @param animation animation url
     * @param disableNotification disable telegram notification
     * @param replyMarkup the reply markup
     */
    public TelegramMessage(String text, String animation, boolean disableNotification, InlineKeyboardMarkup replyMarkup) {
        this.text = text;
        this.animation = animation;
        this.disableNotification = disableNotification;
        this.replyMarkup = replyMarkup;
    }

    /**
     * Telegram Message Constructor
     * @param text telegram content
     * @param images list of image urls
     * @param videos list of video urls
     * @param disableNotification disable telegram notification
     */
    public TelegramMessage(String text, List<String> images, List<String> videos, boolean disableNotification) {
        this.text = text;
        this.images = images;
        this.videos = videos;
        this.disableNotification = disableNotification;
    }

    /**
     * Telegram Message Constructor
     * @param text telegram content
     * @param images list of image urls
     * @param videos list of video urls
     * @param disableNotification disable telegram notification
     * @param replyMarkup the reply markup
     */
    public TelegramMessage(String text, List<String> images, List<String> videos, boolean disableNotification, InlineKeyboardMarkup replyMarkup) {
        this.text = text;
        this.images = images;
        this.videos = videos;
        this.disableNotification = disableNotification;
        this.replyMarkup = replyMarkup;
    }
}
