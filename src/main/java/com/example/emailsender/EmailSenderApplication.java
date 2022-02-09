package com.example.emailsender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import javax.mail.MessagingException;
import java.io.IOException;

@SpringBootApplication
public class EmailSenderApplication {

    private final EmailSenderService emailSenderService;
    private final EmailProperties emailProperties;

    @Autowired
    public EmailSenderApplication(EmailSenderService emailSenderService, EmailProperties emailProperties) {
        this.emailSenderService = emailSenderService;
        this.emailProperties = emailProperties;
    }

    public static void main(String[] args) {
        SpringApplication.run(EmailSenderApplication.class, args);
    }

    //email will be sent when application is ready
    @EventListener(ApplicationReadyEvent.class)
    public void triggerMail(){
        emailSenderService.sendSimpleEmail(emailProperties.getMailTo(),
                "This is a test.",
                "Test Email");
    }

    @EventListener(ApplicationReadyEvent.class)
    public void triggerMailWithAttachment() throws MessagingException {
        emailSenderService.sendEmailWithAttachment(emailProperties.getMailTo(),
                "This is an email with an attachment.",
                "Cat Email",
                "src/main/resources/kitten.jpg");
    }

    @EventListener(ApplicationReadyEvent.class)
    public void triggerHtmlEmail() throws MessagingException, IOException {
        emailSenderService.sendHtmlEmail(emailProperties.getMailTo(),
                "text.html",
                "HTML Email");
    }

}
