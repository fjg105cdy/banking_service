package com.yian.banking_service.services;

public interface EmailService {
    void sendEmail(String to, String name);
    void sendEmailVerificationCode(String to,String code);
}
