package com.secure.jwtimplementation.config;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class EmailConfig {
    private final EmailConfigProperties props;

    @Autowired
    public EmailConfig(EmailConfigProperties props) {
        this.props = props;
    }

    @Bean
    public Session session() {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", props.getAuth());
        prop.put("mail.smtp.starttls.enable", props.getStarttlsEnable());
        prop.put("mail.smtp.host", props.getHost());
        prop.put("mail.smtp.ssl.protocols", props.getSslProtocols());
        prop.put("mail.smtp.port", props.getPort());
        prop.put("mail.smtp.ssl.trust", props.getHost());

        return Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(props.getUsername(), props.getPassword());
            }
        });
    }
}
