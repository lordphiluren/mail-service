package ru.sushchenko.mailsender.service;

import ru.sushchenko.mailsender.dto.AuthNotificationDto;

public interface KafkaMessagingService {
    void sendAuthNotification(AuthNotificationDto authNotificationDto);
}
