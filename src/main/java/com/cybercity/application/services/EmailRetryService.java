package com.cybercity.application.services;

import com.cybercity.application.entities.EmailLogEntity;
import com.cybercity.application.entities.TeamsSessionEntity;
import com.cybercity.application.entities.UserEntity;
import com.cybercity.application.repositories.EmailLogRepository;
import com.cybercity.application.repositories.TeamsSessionRepository;
import com.cybercity.application.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class EmailRetryService {

    @Autowired
    private EmailLogRepository emailLogRepo;

    @Autowired
    private MailService mailService;
    private final TeamsSessionRepository teamsSessionRepository;
    private final UserRepository userRepository;

    @Scheduled(fixedRate = 60000) // every 1 minute
    public void retryFailedEmails() {
        List<EmailLogEntity> failedEmails = emailLogRepo.findByStatus("RETRY_PENDING");
//        List<String> emails = failedEmails.stream().map(emailLogEntity -> emailLogEntity.getRecipient()).toList();
        List<UserEntity> users = failedEmails.stream()
                .map(emailLogEntity -> userRepository.findByEmail(emailLogEntity.getRecipient()))
                .toList();
        List<TeamsSessionEntity> sessions = failedEmails.stream().
                map(emailLogEntity -> teamsSessionRepository.findByTeamsId(emailLogEntity.getBody()))
                .toList();

        int index =0;
        for (EmailLogEntity log : failedEmails) {
            if (log.getAttempts() >= 3) {
                index++;
                continue; // limit retries
            }

            try {
//                emailService.sendEmail(log.getRecipient(), log.getSubject(), log.getBody());
                mailService.sendTeamsMeetingInvite(log.getRecipient(), users.get(index).getUserName(), log.getSubject(), log.getBody(), String.valueOf(sessions.get(index).getStartDate()),sessions.get(index).getDuration(),sessions.get(index).getJoinUrl());

                index++;

                // don't update here — handled by sendEmail()
                log.setAttempts(log.getAttempts() + 1);
                log.setLastAttemptTime(LocalDateTime.now());

            } catch (Exception e) {
                log.setAttempts(log.getAttempts() + 1);
                log.setLastAttemptTime(LocalDateTime.now());
                log.setErrorMessage(e.getMessage());
            }

            emailLogRepo.save(log);
        }
    }
}
