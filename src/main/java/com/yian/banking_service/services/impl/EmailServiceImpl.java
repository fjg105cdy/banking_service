package com.yian.banking_service.services.impl;

import com.yian.banking_service.services.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

//import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;
//    private final Executors executors;

    @Override
    public void sendEmail(String to, String name) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("welcome to banking application");
        message.setText("Welcome to banking application by Yian");

        mailSender.send(message);

    }
}
