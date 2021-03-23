package io.mosfet.scraperone.repository;

import org.awaitility.Awaitility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.concurrent.TimeUnit;

public class TelegramClientTest {

    @Test
    void name() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);

        telegramBotsApi.registerBot(new TelegramLongPollingBot() {
            @Override
            public void onUpdateReceived(Update update) {
                if (update.hasMessage() && update.getMessage().hasText()) {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                    sendMessage.setText("ciaooo");

                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void clearWebhook() {

            }

            @Override
            public String getBotUsername() {
                return "ps5AnnouncerBot";
            }

            @Override
            public String getBotToken() {
                return "1744616718:AAEPEhJtKCf_SWWrhsO5g6dp-obt5KXl_h0";
            }
        });

        Assertions.assertEquals(true, false);
    }
}
