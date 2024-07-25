package com.narzullaev.TelegramBot.service.handler;


import com.narzullaev.TelegramBot.service.manager.feedback.FeedbackManager;
import com.narzullaev.TelegramBot.service.manager.help.HelpManager;
import com.narzullaev.TelegramBot.service.manager.progresscontrol.ProgressControlManager;
import com.narzullaev.TelegramBot.service.manager.task.TaskManager;
import com.narzullaev.TelegramBot.service.manager.timetable.TimetableManager;
import com.narzullaev.TelegramBot.telegram.Bot;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import static com.narzullaev.TelegramBot.service.data.CallbackData.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CallbackQueryHandler {

    HelpManager helpManager;

    FeedbackManager feedbackManager;

    TimetableManager timetableManager;

    TaskManager taskManager;

    ProgressControlManager progressControlManager;

    public BotApiMethod<?> answer(CallbackQuery callbackQuery, Bot bot) {
        String callbackData = callbackQuery.getData();
        String keyWord = callbackData.split("_")[0];
        if (TIMETABLE.equals(keyWord)) {
            return timetableManager.answerCallbackQuery(callbackQuery, bot);
        }
        if (TASK.equals(keyWord)) {
            return taskManager.answerCallbackQuery(callbackQuery, bot);
        }
        if(PROGRESS.equals(keyWord)) {
            return progressControlManager.answerCallbackQuery(callbackQuery, bot);
        }
        switch (callbackData) {
            case FEEDBACK -> {
                return feedbackManager.answerCallbackQuery(callbackQuery, bot);
            }
            case HELP -> {
                return helpManager.answerCallbackQuery(callbackQuery, bot);
            }
        }
        return null;
    }
}
