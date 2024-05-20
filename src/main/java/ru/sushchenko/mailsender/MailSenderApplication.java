package ru.sushchenko.mailsender;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.sushchenko.mailsender.mail.service.impl.MailServiceImpl;

@SpringBootApplication
@AllArgsConstructor
public class MailSenderApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MailSenderApplication.class, args);
    }
}
