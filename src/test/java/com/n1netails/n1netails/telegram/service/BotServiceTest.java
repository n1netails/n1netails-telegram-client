package com.n1netails.n1netails.telegram.service;

import com.n1netails.n1netails.telegram.model.Button;
import com.n1netails.n1netails.telegram.model.InlineKeyboardMarkup;
import com.n1netails.n1netails.telegram.model.TelegramMessage;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.util.Collections;
import java.net.HttpURLConnection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BotServiceTest {

    private BotService botService;
    private HttpURLConnection mockConnection;
    private ByteArrayOutputStream outputStream;
    private String lastApiUrl;

    @BeforeEach
    void setUp() throws Exception {
        mockConnection = mock(HttpURLConnection.class);
        outputStream = new ByteArrayOutputStream();
        when(mockConnection.getOutputStream()).thenReturn(outputStream);
        when(mockConnection.getResponseCode()).thenReturn(200);

        botService = new BotService() {
            @Override
            protected HttpURLConnection openConnection(String apiUrl) throws Exception {
                lastApiUrl = apiUrl;
                return mockConnection;
            }
        };
    }

    @Test
    void testSendTextMessage() throws Exception {
        TelegramMessage message = new TelegramMessage("Hello", false);
        botService.send("chat123", "token123", message);

        assertEquals("https://api.telegram.org/bottoken123/sendMessage", lastApiUrl);
        JSONObject payload = new JSONObject(outputStream.toString());
        assertEquals("chat123", payload.getString("chat_id"));
        assertEquals("Hello", payload.getString("text"));
    }

    @Test
    void testSendAnimationMessage() throws Exception {
        TelegramMessage message = new TelegramMessage("My GIF", "http://example.com/gif.gif", true);
        botService.send("chat123", "token123", message);

        assertEquals("https://api.telegram.org/bottoken123/sendAnimation", lastApiUrl);
        JSONObject payload = new JSONObject(outputStream.toString());
        assertEquals("chat123", payload.getString("chat_id"));
        assertEquals("http://example.com/gif.gif", payload.getString("animation"));
        assertEquals("My GIF", payload.getString("caption"));
        assertEquals(true, payload.getBoolean("disable_notification"));
    }

    @Test
    void testSendAnimationWithKeyboard() throws Exception {
        Button button = new Button("Click me", "http://example.com");
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup(Collections.singletonList(Collections.singletonList(button)));
        TelegramMessage message = new TelegramMessage("My GIF", "http://example.com/gif.gif", false, keyboard);
        botService.send("chat123", "token123", message);

        assertEquals("https://api.telegram.org/bottoken123/sendAnimation", lastApiUrl);
        JSONObject payload = new JSONObject(outputStream.toString());
        assertEquals("chat123", payload.getString("chat_id"));
        assertEquals("http://example.com/gif.gif", payload.getString("animation"));
        assertEquals("My GIF", payload.getString("caption"));
        JSONObject replyMarkup = payload.getJSONObject("reply_markup");
        assertEquals("Click me", replyMarkup.getJSONArray("inline_keyboard").getJSONArray(0).getJSONObject(0).getString("text"));
    }
}
