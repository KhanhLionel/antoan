package com.edu.hcmuaf.fit.webbanvemaybay.services.core;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class MailService {

    private static final String EMAIL = "lenhulinh.130204@gmail.com";
    private static final String APP_PASSWORD = "yrdetaezkbflwasq";

    public static void sendMail(String to,
                                String subject,
                                String content) throws Exception {

        Properties props = new Properties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                EMAIL,
                                APP_PASSWORD
                        );
                    }
                });

        Message message = new MimeMessage(session);

        message.setFrom(new InternetAddress(EMAIL));

        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(to)
        );

        message.setSubject(subject);

        message.setText(content);

        Transport.send(message);
        System.out.println("MAIL SENT SUCCESS");
    }
}
