package com.secure.jwtimplementation.service;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    void sendMail();
    void sendMailAttachment();
}
