package com.cybercity.application.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LessonDTO {
    private Long lessonId;
    private Long courseId;
    private String title;
    private String contentType;
    private String contentUrl;
    private String duration;
    private Long sequenceOrder;
}
