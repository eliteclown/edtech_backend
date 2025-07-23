package com.cybercity.application.advices;

import com.cybercity.application.exceptions.CourseNotFoundException;
import com.cybercity.application.exceptions.EnrollmentNotFoundException;
import com.cybercity.application.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleUserNotFoundException(UserNotFoundException e){
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.NOT_FOUND)
                .error(e.getMessage())
                .build();
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(EnrollmentNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleEnrollmentNotFoundException(EnrollmentNotFoundException e){
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.NOT_FOUND)
                .error(e.getMessage())
                .build();
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleCourseNotFoundException(CourseNotFoundException e){
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.NOT_FOUND)
                .error(e.getMessage())
                .build();
        return buildErrorResponseEntity(apiError);
    }


    public ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(ApiError apiError){
        return new ResponseEntity<>(new ApiResponse<>(apiError),apiError.getStatus());
    }

}
