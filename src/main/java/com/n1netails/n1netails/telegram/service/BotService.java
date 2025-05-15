package com.n1netails.n1netails.telegram.service;

import com.n1netails.n1netails.telegram.exception.TelegramClientException;
import com.n1netails.n1netails.telegram.model.TelegramMessage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

/**
 * Telegram Bot Service
 * @author shahid foy
 */
public class BotService {

    /**
     * Bot Service Constructor
     */
    public BotService() {}

    /**
     * Send telegram notification
     * @param chatId telegram chat id
     * @param botToken telegram bot token
     * @param message telegram message
     * @throws TelegramClientException telegram client exception
     */
    public void send(String chatId, String botToken, TelegramMessage message) throws TelegramClientException {
        try {
            String apiUrl = "https://api.telegram.org/bot" + botToken + "/sendMessage";
            String payload = String.format(
                    "chat_id=%s&text=%s&disable_notification=%b",
                    chatId,
                    encode(message.getText()),
                    message.isDisableNotification()
            );

            HttpURLConnection conn = (HttpURLConnection) new URL(apiUrl).openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            try (OutputStream os = conn.getOutputStream()) {
                os.write(payload.getBytes(StandardCharsets.UTF_8));
            }

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                String errors;
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()))) {
                    errors = reader.lines().collect(Collectors.joining("\n"));
                }
                throw new TelegramClientException("Telegram API responded with HTTP "
                        + responseCode + "\n"
                        + "Errors: " + errors);
            }

        } catch (Exception e) {
            throw new TelegramClientException("Failed to send Telegram message", e);
        }
    }

    private String encode(String text) {
        return text.replace("&", "%26").replace(" ", "%20").replace("\n", "%0A");
    }
}
