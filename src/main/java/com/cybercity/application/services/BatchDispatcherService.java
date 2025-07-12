package com.cybercity.application.services;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BatchDispatcherService {

    private final MailService mailService;

    public void notifyUsers(List<String> emails, List<String> names, String title, String sessionId, String date, String time, String meetingLink) throws MessagingException {
        for (int i = 0; i < emails.size(); i++) {
//            mailService.sendEmail(emails.get(i), subject, body);
            mailService.sendTeamsMeetingInvite(emails.get(i), names.get(i), title,sessionId,date,time,meetingLink);

            // optional: throttle after X emails
            if (i % 100 == 0) {
                try {
                    Thread.sleep(1000); // pause 1 sec after every 100
                } catch (InterruptedException ignored) {}
            }
        }
    }
}
