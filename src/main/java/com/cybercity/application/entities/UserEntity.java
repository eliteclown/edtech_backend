package com.cybercity.application.entities;

import com.cybercity.application.entities.enums.UserType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String userName;
    private String email;
    private String phone;
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<UserType> userType;

    @CreationTimestamp
    private LocalDateTime createDate;

    @CreationTimestamp
    private LocalDateTime updateDate;

    @OneToMany(mappedBy = "userEntity")
    private Set<CourseEntity> courseEntities;

    @OneToMany(mappedBy = "userEntity")
    private Set<PaymentEntity> paymentEntities;
}
