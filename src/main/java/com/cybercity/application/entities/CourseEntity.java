package com.cybercity.application.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "courses")
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long courseId;

    @JoinColumn(name = "user_id",referencedColumnName = "userId")
    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity userEntity;

    private String courseName;
    private String sectionOne;
    private String sectionTwo;
    private String sectionThree;
    private BigDecimal price;

    @CreationTimestamp
    private LocalDateTime createdAt;
    private String category;

    @OneToMany(mappedBy = "courseEntity")
    private List<TeamsSessionEntity> teamsSessionEntities;

    @OneToMany(mappedBy = "courseEntity")
    private List<LessonEntity> lessonEntities;
}
