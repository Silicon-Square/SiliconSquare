package it.siliconsquare.connection.mail;

import it.siliconsquare.logger.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * It manages the sending of emails with or without attachment.
 */
@Component
public class EmailSender {

    private static final String FROM_ADDRESS = "Silicon Square <noreply@siliconsquare.it>";

    @Autowired
    private static JavaMailSender emailSender;
    private static MailConfig mailConfig;

    static {
        mailConfig = new MailConfig();
        emailSender = mailConfig.getJavaMailSender();
    }

    public static void sendSimpleMessage(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(FROM_ADDRESS);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            emailSender.send(message);
            Logger.getInstance().captureMessage("Email Sent: to = [" + to + "]");
        } catch (Exception e) {
            Logger.getInstance().captureException(e, "Email FAILED to send");
        }
    }

    public static void sendMessageWithAttachment(String to, String subject, String text, String attachmentPath) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(FROM_ADDRESS);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);

            FileSystemResource file = new FileSystemResource(new File(attachmentPath));
            helper.addAttachment(file.getFilename(), file);
            emailSender.send(message);
            Logger.getInstance().captureMessage("Email With Attachment Sent: to = [" + to + "]");
        } catch (MessagingException e) {
            Logger.getInstance().captureException(e, "Email with attachment FAILED to send");
        }
    }
}

