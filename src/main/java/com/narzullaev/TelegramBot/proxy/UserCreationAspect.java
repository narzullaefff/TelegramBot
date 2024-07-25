package com.narzullaev.TelegramBot.proxy;


import com.narzullaev.TelegramBot.entities.Action;
import com.narzullaev.TelegramBot.entities.Role;
import com.narzullaev.TelegramBot.entities.UserDetails;
import com.narzullaev.TelegramBot.repositories.DetailsRepository;
import com.narzullaev.TelegramBot.repositories.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.time.LocalDateTime;

@Aspect
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserCreationAspect {

    UserRepository userRepository;
    DetailsRepository detailsRepository;

    @Pointcut("execution(* com.narzullaev.TelegramBot.service.UpdateDispatcher.distribute(..))")
    public void distributeMethodPointcut() {
    }

    @Around("distributeMethodPointcut()")
    public Object distributeMethodAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Update update = (Update) args[0];
        User telegramUser;
        if (update.hasMessage()) {
            telegramUser = update.getMessage().getFrom();
        } else if (update.hasCallbackQuery()) {
            telegramUser = update.getCallbackQuery().getFrom();
        } else {
            return joinPoint.proceed();
        }
        if (userRepository.existsById(telegramUser.getId())) {
            return joinPoint.proceed();
        }

        UserDetails details = UserDetails.builder()
                .firstName(telegramUser.getFirstName())
                .username(telegramUser.getUserName())
                .lastName(telegramUser.getLastName())
                .registeredAt(LocalDateTime.now())
                .build();
        detailsRepository.save(details);
        com.narzullaev.TelegramBot.entities.user.User newUser =
                com.narzullaev.TelegramBot.entities.user.User.builder()
                        .chatId(telegramUser.getId())
                        .action(Action.FREE)
                        .role(Role.USER)
                        .details(details)
                        .build();
        userRepository.save(newUser);

        return joinPoint.proceed();
    }
}
