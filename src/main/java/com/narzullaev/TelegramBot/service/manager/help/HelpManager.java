package com.narzullaev.TelegramBot.service.manager.help;


import com.narzullaev.TelegramBot.service.factory.AnswerMethodFactory;
import com.narzullaev.TelegramBot.service.factory.KeyboardFactory;
import com.narzullaev.TelegramBot.service.manager.AbstractManager;
import com.narzullaev.TelegramBot.telegram.Bot;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HelpManager extends AbstractManager {

    AnswerMethodFactory answerMethodFactory;

    KeyboardFactory keyboardFactory;

    @Override
    public BotApiMethod<?> answerCommand(Message message, Bot bot) {
        return answerMethodFactory.getSendMessage(
                message.getChatId(),
                """
                        📍Доступные команды:
                        - start
                        - help
                        - feedback
                                                
                        📍Доступные функции:
                        - Расписание
                        - Домашнее задание
                        - Контроль успеваемости
                        """,
                null
        );
    }

    @Override
    public BotApiMethod<?> answerCallbackQuery(CallbackQuery callbackQuery, Bot bot) {
        return answerMethodFactory.getEditMessageText(
                callbackQuery,
                """
                        📍Доступные команды:
                        - start
                        - help
                        - feedback
                                                
                        📍Доступные функции:
                        - Расписание
                        - Домашнее задание
                        - Контроль успеваемости
                        """,
                null
        );
    }

    @Override
    public BotApiMethod<?> answerMessage(Message message, Bot bot) {
        return null;
    }

}
