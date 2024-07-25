package com.narzullaev.TelegramBot.service.handler;


import com.narzullaev.TelegramBot.service.manager.feedback.FeedbackManager;
import com.narzullaev.TelegramBot.service.manager.help.HelpManager;
import com.narzullaev.TelegramBot.service.manager.progresscontrol.ProgressControlManager;
import com.narzullaev.TelegramBot.service.manager.start.StartManager;
import com.narzullaev.TelegramBot.service.manager.task.TaskManager;
import com.narzullaev.TelegramBot.service.manager.timetable.TimetableManager;
import com.narzullaev.TelegramBot.telegram.Bot;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;


import static com.narzullaev.TelegramBot.service.data.Command.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommandHandler {

    HelpManager helpManager;

    FeedbackManager feedbackManager;

    StartManager startManager;

    TimetableManager timetableManager;

    TaskManager taskManager;

    ProgressControlManager progressControlManager;

    public BotApiMethod<?> answer(Message message, Bot bot) {
        String command = message.getText();
        switch (command) {
            case START -> {
                return startManager.answerCommand(message, bot);
            }
            case FEEDBACK_COMMAND -> {
                return feedbackManager.answerCommand(message, bot);
            }
            case HELP_COMMAND -> {
                return helpManager.answerCommand(message, bot);
            }
            case TIMETABLE -> {
                return timetableManager.answerCommand(message, bot);
            }
            case TASK -> {
                return taskManager.answerCommand(message, bot);
            }
            case PROGRESS -> {
                return progressControlManager.answerCommand(message, bot);
            }
            default -> {
                return defaultAnswer(message);
            }
        }
    }

    public BotApiMethod<?> defaultAnswer(Message message) {
        return SendMessage.builder()
                .chatId(message.getChatId())
                .text("Недоступная команда!")
                .build();
    }
}
