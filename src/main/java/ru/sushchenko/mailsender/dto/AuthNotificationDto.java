package ru.sushchenko.mailsender.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthNotificationDto {
    private String email;
    private String username;
}
