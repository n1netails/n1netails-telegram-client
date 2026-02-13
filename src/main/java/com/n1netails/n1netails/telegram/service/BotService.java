package com.n1netails.n1netails.telegram.service;

import com.n1netails.n1netails.telegram.exception.TelegramClientException;
import com.n1netails.n1netails.telegram.model.TelegramMessage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.util.List;
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
            String method;
            JSONObject jsonPayload = new JSONObject();
            jsonPayload.put("chat_id", chatId);

            List<String> images = message.getImages();
            List<String> videos = message.getVideos();
            int imageCount = (images != null) ? images.size() : 0;
            int videoCount = (videos != null) ? videos.size() : 0;
            int totalMedia = imageCount + videoCount;

            if (totalMedia > 1) {
                method = "sendMediaGroup";
                JSONArray mediaArray = new JSONArray();
                if (images != null) {
                    for (int i = 0; i < images.size(); i++) {
                        JSONObject media = new JSONObject();
                        media.put("type", "photo");
                        media.put("media", images.get(i));
                        if (i == 0 && message.getText() != null) {
                            media.put("caption", message.getText());
                        }
                        mediaArray.put(media);
                    }
                }
                if (videos != null) {
                    for (int i = 0; i < videos.size(); i++) {
                        JSONObject media = new JSONObject();
                        media.put("type", "video");
                        media.put("media", videos.get(i));
                        if (imageCount == 0 && i == 0 && message.getText() != null) {
                            media.put("caption", message.getText());
                        }
                        mediaArray.put(media);
                    }
                }
                jsonPayload.put("media", mediaArray);
            } else if (imageCount == 1) {
                method = "sendPhoto";
                jsonPayload.put("photo", images.get(0));
                if (message.getText() != null) {
                    jsonPayload.put("caption", message.getText());
                }
            } else if (videoCount == 1) {
                method = "sendVideo";
                jsonPayload.put("video", videos.get(0));
                if (message.getText() != null) {
                    jsonPayload.put("caption", message.getText());
                }
            } else if (message.getAnimation() != null && !message.getAnimation().isEmpty()) {
                method = "sendAnimation";
                jsonPayload.put("animation", message.getAnimation());
                if (message.getText() != null) {
                    jsonPayload.put("caption", message.getText());
                }
            } else {
                method = "sendMessage";
                jsonPayload.put("text", message.getText());
            }

            jsonPayload.put("disable_notification", message.isDisableNotification());

            if (message.getReplyMarkup() != null && !method.equals("sendMediaGroup")) {
                jsonPayload.put("reply_markup", new JSONObject(message.getReplyMarkup()));
            }

            String apiUrl = "https://api.telegram.org/bot" + botToken + "/" + method;
            HttpURLConnection conn = openConnection(apiUrl);
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");

            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonPayload.toString().getBytes(StandardCharsets.UTF_8));
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

    protected HttpURLConnection openConnection(String apiUrl) throws Exception {
        return (HttpURLConnection) new URL(apiUrl).openConnection();
    }
}
