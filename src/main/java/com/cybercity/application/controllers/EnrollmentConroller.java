package com.cybercity.application.controllers;

import com.cybercity.application.dtos.EnrollmentDTO;
import com.cybercity.application.services.EnrollmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enroll")
public class EnrollmentConroller {
    private final EnrollmentService enrollmentService;

    public EnrollmentConroller(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping("/{userId}/{courseId}")
    public EnrollmentDTO enrollNewCourse(@PathVariable Long userId, @PathVariable Long courseId, @RequestParam String category){
        return enrollmentService.enrollNewUser(userId,courseId,category);
    }

    @GetMapping("/get/{userId}")
    public List<EnrollmentDTO> getEnrolledCourseByUserId(@PathVariable Long userId){
        return enrollmentService.courseEnrolledByUserId(userId);
    }

}
