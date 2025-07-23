package com.cybercity.application.services;

import com.cybercity.application.entities.EmailLogEntity;
import com.cybercity.application.repositories.EmailLogRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;

//@Service
//public class MailService {
//
//    @Autowired
//    private JavaMailSender mailSender;
//
//    @Autowired
//    private TemplateEngine templateEngine;
//
//    @Async("emailExecutor")
//    public void sendTeamsMeetingInvite(String to, String name, String title, String sessionId, String date, String time, String meetingLink) throws MessagingException, MessagingException {
//        MimeMessage mimeMessage = mailSender.createMimeMessage();
//
//        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//        helper.setFrom("karthikboot12@gmail.com");
//        helper.setTo(to);
//        helper.setSubject(title);
//
//        // Set Thymeleaf context
//        Context context = new Context();
//        context.setVariable("name", name);
//        context.setVariable("title", title);
//        context.setVariable("sessionId", sessionId);
//        context.setVariable("date", date);
//        context.setVariable("time", time);
//        context.setVariable("meetingLink", meetingLink);
//
//        String html = templateEngine.process("email-template", context);
//        helper.setText(html, true);
//
//        mailSender.send(mimeMessage);
//    }
//
//}



@Service
@RequiredArgsConstructor
public class MailService {


    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;
    private final EmailLogRepository emailLogRepository;

    @Async("emailExecutor")
    public void sendTeamsMeetingInvite(String to, String name, String title, String sessionId, String date, String time, String meetingLink) {

        EmailLogEntity log = new EmailLogEntity();
        log.setRecipient(to);
        log.setSubject(title);
        log.setBody(sessionId);
        log.setAttempts(1);
        log.setLastAttemptTime(LocalDateTime.now());

        try {
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
            log.setStatus("SENT");
            emailLogRepository.save(log);


        } catch (MessagingException e) {
            // 👇 Log or handle the error

            log.setStatus("RETRY_PENDING");
            log.setErrorMessage(e.getMessage());


            // Optional: save to email_logs table or retry queue here
        } catch (Exception e) {
            // Catch any other exceptions (template issues, etc.)
            System.err.println("Unexpected error while sending email to: " + to);
            e.printStackTrace();
        }
    }
}

