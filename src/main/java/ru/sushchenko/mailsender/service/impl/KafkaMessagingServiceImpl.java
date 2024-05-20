package ru.sushchenko.mailsender.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.sushchenko.mailsender.dto.AuthNotificationDto;
import ru.sushchenko.mailsender.dto.UserHotTaskDto;
import ru.sushchenko.mailsender.mail.service.MailService;
import ru.sushchenko.mailsender.service.KafkaMessagingService;

@Slf4j
@Service
@Configuration
@RequiredArgsConstructor
public class KafkaMessagingServiceImpl implements KafkaMessagingService {
    private final MailService mailService;

    @Override
    @KafkaListener(
            topics = "${kafka-topic.auth}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "authKafkaListenerContainerFactory"
    )
    public void sendAuthNotification(AuthNotificationDto authNotificationDto) {
        mailService.sendHelloMessage(authNotificationDto);
    }

    @KafkaListener(
            topics = "${kafka-topic.notification}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "notificationKafkaListenerContainerFactory"
    )
    public void handleHotTaskNotification(UserHotTaskDto hotTaskNotificationDto) {
        mailService.sendHotTaskNotification(hotTaskNotificationDto);
    }
}
