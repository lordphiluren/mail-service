package ru.sushchenko.mailsender.mail.service;

import ru.sushchenko.mailsender.dto.AuthNotificationDto;
import ru.sushchenko.mailsender.dto.UserHotTaskDto;

public interface MailService {
    void sendHelloMessage(AuthNotificationDto authNotificationResponse);

    void sendHotTaskNotification(UserHotTaskDto hotTaskNotificationDto);
}
