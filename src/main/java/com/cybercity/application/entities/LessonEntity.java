package com.cybercity.application.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "lessons")
public class LessonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long lessonId;

    @JoinColumn(name = "course_id",referencedColumnName = "courseId")
    @ManyToOne(fetch = FetchType.LAZY)
    private CourseEntity courseEntity;
    private String title;
    private String contentType;
    private String contentUrl;
    private String duration;
    private Long sequenceOrder;
}
