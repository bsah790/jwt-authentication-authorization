package com.secure.jwtimplementation.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "mail.smtp")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailConfigProperties {
    private String host;
    private Integer port;
    private Boolean auth;
    private String sslTrust;
    private String starttlsEnable;
    private String sslProtocols;
    private String username;
    private String password;
}
