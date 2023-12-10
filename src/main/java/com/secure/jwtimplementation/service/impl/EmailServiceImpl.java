package com.secure.jwtimplementation.service.impl;

import com.secure.jwtimplementation.constant.EmailConstant;
import com.secure.jwtimplementation.service.EmailService;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class EmailServiceImpl implements EmailService {
    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
    @Autowired
    Session session;

    @Autowired
    Environment env;

    private Address[] getToAddress(String toAddress) {
       return Arrays.stream(toAddress.split(",", -1)).map(e -> {
            try {
                return new InternetAddress(e);
            } catch (AddressException ex) {
                throw new RuntimeException(ex);
            }
        }).collect(Collectors.toList()).toArray(new InternetAddress[0]);
    }

    @Override
    public void sendMail() {
        String toAddress = env.getProperty("mail.receiver");
        String fromAddress = env.getProperty("mail.sender");
        Address[] toEmailArr = getToAddress(toAddress);
        logger.info("Sending Email to {}", toAddress);
        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);
            // Set From
            message.setFrom(new InternetAddress(fromAddress, EmailConstant.FROM_NAME));
            // Set To
            message.addRecipients(Message.RecipientType.TO, toEmailArr);
            // Set Subject
            message.setSubject(EmailConstant.EMAIL_SUBJECT);
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(EmailConstant.MESSAGE, "text/html");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            message.setContent(multipart);
            // Send message
            Transport.send(message);
            logger.info(EmailConstant.SUCESSFUL_RESULT);
        } catch (Exception ex) {
            logger.error("Failed to send email to: {}", toAddress);
            logger.error("Exception is: {}", ex.getMessage());
        }
    }

    @Override
    public void sendMailAttachment() {

    }
}
