package com.yian.banking_service.services.impl;

import com.yian.banking_service.services.EmailService;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.concurrent.Executor;

//import java.util.concurrent.Executors;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;
    private final Executor executor;

    public EmailServiceImpl(
            JavaMailSender mailSender,
            SpringTemplateEngine templateEngine,
            @Qualifier("emailExecutor") Executor executor
    ){
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
        this.executor = executor;
    }

    @Override
    public void sendEmail(String to, String name) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(to);
//        message.setSubject("welcome to banking application");
//        message.setText("Welcome to banking application by Yian");
//
//        mailSender.send(message);

        executor.execute(()->{
            try{

                MimeMessage mimeMessage = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

                //템플릿 html 처리
                Context context = new Context();
                context.setVariable("name", name);
                String htmlContent = templateEngine.process("welcome-email",context);

                helper.setTo(to);
                helper.setSubject("Welcome to Banking Application");
                helper.setText(htmlContent, true);

                mailSender.send(mimeMessage);

            } catch (Exception e){
                System.err.println("Email send fail"+e.getMessage());
                e.printStackTrace();
            }
        });

    }
}
