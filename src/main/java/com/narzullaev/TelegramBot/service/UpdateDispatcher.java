package com.narzullaev.TelegramBot.service;


import com.narzullaev.TelegramBot.entities.user.User;
import com.narzullaev.TelegramBot.repositories.UserRepository;
import com.narzullaev.TelegramBot.service.handler.CallbackQueryHandler;
import com.narzullaev.TelegramBot.service.handler.CommandHandler;
import com.narzullaev.TelegramBot.service.handler.MessageHandler;
import com.narzullaev.TelegramBot.telegram.Bot;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class UpdateDispatcher {


    MessageHandler messageHandler;

    CommandHandler commandHandler;

    CallbackQueryHandler callbackQueryHandler;


    public BotApiMethod<?> distribute(Update update, Bot bot) {
        if (update.hasCallbackQuery()) {
            return callbackQueryHandler.answer(update.getCallbackQuery(), bot);
        }
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.hasText()) {
                if (message.getText().charAt(0) == '/') {
                    return commandHandler.answer(message, bot);
                }
            }
            return messageHandler.answer(message, bot);
        }
        log.info("Unsupported update: " + update);
        return null;
    }
}
