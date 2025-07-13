package com.cybercity.application.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "emaillogs")
public class EmailLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String recipient;
    private String subject;
    private String body;

    private String status; // SENT, FAILED, RETRY_PENDING
    private int attempts;

    private LocalDateTime lastAttemptTime;

    @Column(columnDefinition = "TEXT")
    private String errorMessage;

    private LocalDateTime createdAt = LocalDateTime.now();

}
