package com.narzullaev.TelegramBot.service.manager.progresscontrol;

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

import java.util.List;

import static com.narzullaev.TelegramBot.service.data.CallbackData.PROGRESS;
import static com.narzullaev.TelegramBot.service.data.CallbackData.PROGRESS_STAT;


@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProgressControlManager extends AbstractManager {

    AnswerMethodFactory answerMethodFactory;

    KeyboardFactory keyboardFactory;

    @Override
    public BotApiMethod<?> answerCommand(Message message, Bot bot) {
        return mainMenu(message);
    }

    @Override
    public BotApiMethod<?> answerMessage(Message message, Bot bot) {
        return null;
    }

    @Override
    public BotApiMethod<?> answerCallbackQuery(CallbackQuery callbackQuery, Bot bot) {
        String callbackData = callbackQuery.getData();
        switch (callbackData) {
            case PROGRESS -> {
                return mainMenu(callbackQuery);
            }
            case PROGRESS_STAT -> {
                return stat(callbackQuery);
            }
        }
        return null;
    }

    private BotApiMethod<?> mainMenu(CallbackQuery callbackQuery) {
        return answerMethodFactory.getEditMessageText(
                callbackQuery,
                """
                        Здесь вы можете увидеть""",
                keyboardFactory.getInlineKeyboard(
                        List.of("Статистика успеваемости"),
                        List.of(1),
                        List.of(PROGRESS_STAT)
                )
        );
    }

    private BotApiMethod<?> mainMenu(Message message) {
        return answerMethodFactory.getSendMessage(
                message.getChatId(),
                """
                        Здесь вы можете увидеть""",
                keyboardFactory.getInlineKeyboard(
                        List.of("Статистика успеваемости"),
                        List.of(1),
                        List.of(PROGRESS_STAT)
                )
        );
    }

    private BotApiMethod<?> stat(CallbackQuery callbackQuery) {
        return answerMethodFactory.getEditMessageText(
                callbackQuery,
                "Здесь будет статистика",
                keyboardFactory.getInlineKeyboard(
                        List.of("Назад"),
                        List.of(1),
                        List.of(PROGRESS)
                )
        );
    }
}
