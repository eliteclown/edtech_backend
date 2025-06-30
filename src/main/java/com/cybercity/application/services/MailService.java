package com.cybercity.application.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public void sendTeamsMeetingInvite(String to, String name, String title, String sessionId, String date, String time, String meetingLink) throws MessagingException, MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("karthikboot12@gmail.com");
        helper.setTo(to);
        helper.setSubject(title);

        // Set Thymeleaf context
        Context context = new Context();
        context.setVariable("name", name);
        context.setVariable("title", title);
        context.setVariable("sessionId", sessionId);
        context.setVariable("date", date);
        context.setVariable("time", time);
        context.setVariable("meetingLink", meetingLink);

        String html = templateEngine.process("email-template", context);
        helper.setText(html, true);

        mailSender.send(mimeMessage);
    }

}

