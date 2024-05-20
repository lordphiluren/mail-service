package ru.sushchenko.mailsender.mail.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import ru.sushchenko.mailsender.dto.AuthNotificationDto;
import ru.sushchenko.mailsender.dto.HotTaskDto;
import ru.sushchenko.mailsender.dto.UserHotTaskDto;
import ru.sushchenko.mailsender.mail.EmailSender;
import ru.sushchenko.mailsender.mail.service.MailService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final EmailSender emailSender;
    @Value("${spring.mail.sender}")
    private String senderEmail;
    @Override
    public void sendHelloMessage(AuthNotificationDto authNotificationDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderEmail);
        message.setTo(authNotificationDto.getEmail());
        message.setSubject("Welcome to TrelloClone!");
        message.setText("Hello " + authNotificationDto.getUsername() + "! You are successfully registered in Trello Clone application.");
        emailSender.sendSimpleMessage(message);
    }

    @Override
    public void sendHotTaskNotification(UserHotTaskDto hotTaskNotificationDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderEmail);
        message.setTo(hotTaskNotificationDto.getEmail());
        message.setSubject("Attention! | TrelloClone");

        StringBuilder messageText = new StringBuilder();
        messageText.append("Hello, ").append(hotTaskNotificationDto.getUsername()).append("!\n");
        messageText.append("Be careful, these tasks will end today:\n\n");

        List<HotTaskDto> hotTasks = hotTaskNotificationDto.getHotTasks();
        for (int i = 0; i < hotTasks.size(); i++) {
            HotTaskDto task = hotTasks.get(i);
            messageText.append(i + 1).append(". ");
            messageText.append("Name: ").append(task.getName()).append("\n");
            messageText.append("   Status: ").append(task.getStatus()).append("\n");
            messageText.append("   Priority: ").append(task.getPriority()).append("\n\n");
        }

        message.setText(messageText.toString());

        emailSender.sendSimpleMessage(message);
    }
}