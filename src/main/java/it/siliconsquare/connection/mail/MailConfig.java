package it.siliconsquare.connection.mail;

import it.siliconsquare.logger.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class MailConfig {

    @Bean(name = "mailSource")
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        Properties props = mailSender.getJavaMailProperties();

        Properties properties = new Properties();
        URL applicationProperty = this.getClass().getClassLoader().getResource("application.properties");
        try {
            properties.load(new FileInputStream(applicationProperty.getPath()));
        } catch (IOException e) {
            Logger.getInstance().captureException(e);
        }
        String username = properties.getProperty("spring.mail.username");
        String password = properties.getProperty("spring.mail.password");
        String port = properties.getProperty("spring.mail.properties.mail.smtp.port");
        String smtpAuth = properties.getProperty("spring.mail.properties.mail.smtp.auth");
        String smtpTtl = properties.getProperty("spring.mail.properties.mail.smtp.starttls.enable");
        String host = properties.getProperty("spring.mail.host");
        //String debug = properties.getProperty("spring.mail.properties.mail.debug");

        mailSender.setUsername(username);
        mailSender.setPassword(password);
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", smtpAuth);
        props.put("mail.smtp.starttls.enable", smtpTtl);
        return mailSender;
    }

}
