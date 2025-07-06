package com.cybercity.application.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    private Long courseId;
    private Long userId;
    private String courseName;
    private String sectionOne;
    private String sectionTwo;
    private String sectionThree;
    private List<String> photos;
    private BigDecimal price;
    private LocalDateTime createdAt;
    private String category;
}
