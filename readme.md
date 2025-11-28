# N1netails

<div align="center">
  <img src="https://raw.githubusercontent.com/n1netails/n1netails/refs/heads/main/n1netails_icon_transparent.png" alt="N1ne Tails" width="500" style="display: block; margin: auto;"/>
</div>

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

![Stars](https://img.shields.io/github/stars/n1netails/n1netails-telegram-client)
![Issues](https://img.shields.io/github/issues/n1netails/n1netails-telegram-client)
![Contributors](https://img.shields.io/github/contributors/n1netails/n1netails-telegram-client)
![Last Commit](https://img.shields.io/github/last-commit/n1netails/n1netails-telegram-client)

# Telegram Client
N1netails is an open-source project that provides practical alerts and monitoring for applications.
Use the N1netails Telegram Client to easily send messages to a telegram chat group.

## How to set up telegram
Telegram is an application you can download on your smartphone. You can download Telegram here
https://telegram.org/

** Note: Telegram needs to be set up on your smartphone before you can use it on your desktop. **

### How to create a telegram bot

To send messages using the N1ne Tails Telegram Client, you need to create a Telegram bot and retrieve its **bot token**. Follow these steps:

#### 1. Start a chat with [@BotFather](https://t.me/BotFather)

[@BotFather](https://t.me/BotFather) is the official Telegram bot for creating and managing other bots.

#### 2. Create a new bot

Send the following command to BotFather:

```
/newbot
```

You will be prompted to:

* **Name your bot** (this can be anything, like `N1neTailsBot`)
* **Choose a username** (this must end with `bot`, e.g. `n1netails_alertbot`)

#### 3. Get your bot token

After creating your bot, BotFather will send you an HTTP API token that looks like this:

```
123456789:ABCdefGhIJKlmNoPQRstuVWXyz
```

> 📌 **Important:** Keep this token secure. Anyone with access can control your bot.

#### 4. Add the bot to a chat or group

* Create or open an existing Telegram **group chat**.
* Add your bot to the group like you would any other user.
* Ensure the bot has permission to **send messages** in the group.

#### 5. Get the chat ID

To send messages to a group or individual chat, you’ll need the chat ID. Here are two common methods:

**Option A: Use a third-party bot like [@getidsbot](https://t.me/getidsbot)**

* Add `@getidsbot` to your group or start a direct chat.
* It will reply with the chat ID.

**Option B: Use the Telegram Bot API**
Send a request to the `getUpdates` endpoint using your bot token:

```bash
curl https://api.telegram.org/bot<YOUR_BOT_TOKEN>/getUpdates
```

Look for `"chat":{"id":...}` in the response.

---

You can now use the **bot token** and **chat ID** in your Java code to send alerts with the `n1netails-telegram-client`.

---


Resources: 
- https://core.telegram.org/
- https://core.telegram.org/methods

## Install
Install the telegram client by adding the following dependency:
```xml
<dependency>
    <groupId>com.n1netails</groupId>
    <artifactId>n1netails-telegram-client</artifactId>
    <version>0.2.0</version>
</dependency>
```

Gradle (Groovy)
```groovy
implementation 'com.n1netails:n1netails-telegram-client:0.2.0'
```

## Configure
Here is how you can configure the project for different frameworks

### Spring Boot
Add the following beans to your spring boot application:

```java
import com.n1netails.n1netails.telegram.api.TelegramClient;
import com.n1netails.n1netails.telegram.internal.TelegramClientImpl;
import com.n1netails.n1netails.telegram.service.BotService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TelegramConfig {

    @Bean
    public BotService botService() { 
        return new BotService(); 
    }

    @Bean
    public TelegramClient telegramClient(BotService service) {
        return new TelegramClientImpl(service);
    }
}
```

### Java

```java
import com.n1netails.n1netails.telegram.internal.TelegramClientImpl;
import com.n1netails.n1netails.telegram.service.BotService;

BotService service = new BotService();
TelegramClient client = new TelegramClientImpl(service);
```

## Use
```java
import com.n1netails.n1netails.telegram.api.TelegramClient;
import com.n1netails.n1netails.telegram.internal.TelegramClientImpl;
import com.n1netails.n1netails.telegram.model.Button;
import com.n1netails.n1netails.telegram.model.InlineKeyboardMarkup;
import com.n1netails.n1netails.telegram.model.TelegramMessage;
import com.n1netails.n1netails.telegram.service.BotService;

import java.util.Collections;

public class ExampleService {
    private final TelegramClient telegramClient;

    public ExampleService() {
        this.telegramClient = new TelegramClientImpl(new BotService());
    }

    public void telegramNotificationExample(String content) {
        Button button = new Button("Visit N1netails", "https://n1netails.com");
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup(Collections.singletonList(Collections.singletonList(button)));
        TelegramMessage telegramMessage = new TelegramMessage("N1netails Telegram Works!", false, keyboardMarkup);
        // replace with your telegram chat id
        String chatId = "your-telegram-chat-id";
        // replace with your telegram bot token
        String botToken = "your-telegram-bot-token";
        telegramClient.sendMessage(chatId, botToken, telegramMessage);
    }
}
```

#### Example message output
<div align="center">
  <img src="telegram-message.png" alt="N1netails telegram message simple" width="500" style="display: block; margin: auto;"/>
</div>

# Develop
## Build
Build the project using the following command
```bash
mvn clean install
```

## Maven Central Repository
Use the following doc to get setup with publishing to the maven central repository
https://central.sonatype.org/register/central-portal/#publishing

Maven install using release profile.
```bash
mvn clean install -P release
```

Maven deploy to the maven central repository
```bash
mvn deploy -P release
```

## Support

For community users, open an issue on GitHub or Join our Discord

[![Join our Discord](https://img.shields.io/badge/Join_Discord-7289DA?style=for-the-badge&logo=discord&logoColor=white)](https://discord.gg/ma9CCw7F2x)

## Contributing

Please use the following guidelines for contributions [CONTRIBUTING](./contributing.md)

## N1netails Telegram Client Contributors

Thanks to all the amazing people who contributed to N1netails Telegram Client! 💙

[![Contributors](https://contrib.rocks/image?repo=n1netails/n1netails-telegram-client)](https://github.com/n1netails/n1netails-telegram-client/graphs/contributors)