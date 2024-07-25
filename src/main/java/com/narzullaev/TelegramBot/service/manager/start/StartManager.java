package com.narzullaev.TelegramBot.service.manager.start;


import com.narzullaev.TelegramBot.service.factory.AnswerMethodFactory;
import com.narzullaev.TelegramBot.service.factory.KeyboardFactory;
import com.narzullaev.TelegramBot.service.manager.AbstractManager;
import com.narzullaev.TelegramBot.telegram.Bot;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

import static com.narzullaev.TelegramBot.service.data.CallbackData.FEEDBACK;
import static com.narzullaev.TelegramBot.service.data.CallbackData.HELP;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StartManager extends AbstractManager {

    AnswerMethodFactory answerMethodFactory;

    KeyboardFactory keyboardFactory;

    @Override
    public SendMessage answerCommand(Message message, Bot bot) {
        return answerMethodFactory.getSendMessage(
                message.getChatId(),
                """
                        🖖 Привет дорогой ученик!

                        Что я могу?
                        📌 Составлять расписание
                        📌 Прикреплять домашние задания
                        📌 Ввести контроль успеваемости
                        """,
                keyboardFactory.getInlineKeyboard(
                        List.of("Помощь", "Обратная связь"),
                        List.of(2),
                        List.of(HELP, FEEDBACK)
                )
        );
    }


    @Override
    public BotApiMethod<?> answerMessage(Message message, Bot bot) {
        return null;
    }

    @Override
    public BotApiMethod<?> answerCallbackQuery(CallbackQuery callbackQuery, Bot bot) {
        return null;
    }
}
