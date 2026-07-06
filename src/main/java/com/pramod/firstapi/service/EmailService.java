package com.pramod.firstapi.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final Logger logger =
            LogManager.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender mailSender;

    public String sendEmail(String to,
                            String subject,
                            String message) {

        logger.info("Preparing to send email to : {}", to);

        SimpleMailMessage mail =
                new SimpleMailMessage();

        mail.setTo(to);
        mail.setSubject(subject);
        mail.setText(message);

        try {

            mailSender.send(mail);

            logger.info("Email sent successfully to : {}", to);

            return "Email Sent Successfully";

        } catch (Exception e) {

            logger.error("Failed to send email to : {}", to, e);

            return "Failed to Send Email";
        }
    }
}