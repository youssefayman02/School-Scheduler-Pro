package com.project.schoolschedulingsystem.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = SchoolNotFoundException.class)
    public ResponseEntity<Object> handleSchoolNotFoundException(SchoolNotFoundException e) {
        return new ResponseEntity<>(
                new ApiException(e.getMessage(), e, HttpStatus.BAD_REQUEST, ZonedDateTime.now())
                , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = TeacherNotFoundException.class)
    public ResponseEntity<Object> handleTeacherNotFoundException(TeacherNotFoundException e) {
        return new ResponseEntity<>(
                new ApiException(e.getMessage(), e, HttpStatus.BAD_REQUEST, ZonedDateTime.now())
                , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = SubjectNotFoundException.class)
    public ResponseEntity<Object> handleSubjectNotFoundException(SubjectNotFoundException e) {
        return new ResponseEntity<>(
                new ApiException(e.getMessage(), e, HttpStatus.BAD_REQUEST, ZonedDateTime.now())
                , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = SubjectTeacherAssignmentNotFoundException.class)
    public ResponseEntity<Object> handleSubjectTeacherAssignmentNotFoundException(SubjectTeacherAssignmentNotFoundException e) {
        return new ResponseEntity<>(
                new ApiException(e.getMessage(), e, HttpStatus.BAD_REQUEST, ZonedDateTime.now())
                , HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(value = SlotNotFoundException.class)
//    public ResponseEntity<Object> handleSlotNotFoundException(SlotNotFoundException e) {
//        return new ResponseEntity<>(
//                new ApiException(e.getMessage(), e, HttpStatus.BAD_REQUEST, ZonedDateTime.now())
//                , HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(value = ClassLimitExceededException.class)
    public ResponseEntity<Object> handleClassLimitExceededException(ClassLimitExceededException e) {
        return new ResponseEntity<>(
                new ApiException(e.getMessage(), e, HttpStatus.BAD_REQUEST, ZonedDateTime.now())
                , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ClassNotFoundException.class)
    public ResponseEntity<Object> handleClassNotFoundException(ClassNotFoundException e) {
        return new ResponseEntity<>(
                new ApiException(e.getMessage(), e, HttpStatus.BAD_REQUEST, ZonedDateTime.now())
                , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = GradeLimitExceededException.class)
    public ResponseEntity<Object> handleGradeLimitExceededException(GradeLimitExceededException e) {
        return new ResponseEntity<>(
                new ApiException(e.getMessage(), e, HttpStatus.BAD_REQUEST, ZonedDateTime.now())
                , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = GradeNotFoundException.class)
    public ResponseEntity<Object> handleGradeNotFoundException(GradeNotFoundException e) {
        return new ResponseEntity<>(
                new ApiException(e.getMessage(), e, HttpStatus.BAD_REQUEST, ZonedDateTime.now())
                , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = StudentNotFoundException.class)
    public ResponseEntity<Object> handleStudentNotFoundException(StudentNotFoundException e) {
        return new ResponseEntity<>(
                new ApiException(e.getMessage(), e, HttpStatus.BAD_REQUEST, ZonedDateTime.now())
                , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = SlotNotFoundException.class)
    public ResponseEntity<Object> handleSlotNotFoundException(SlotNotFoundException e)
    {
        return new ResponseEntity<>(
                new ApiException(
                        e.getMessage(),
                        e,
                        HttpStatus.BAD_REQUEST,
                        ZonedDateTime.now()
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(value = SlotLimitExceededException.class)
    public ResponseEntity<Object> handleSlotLimitExceededException(SlotLimitExceededException e)
    {
        return new ResponseEntity<>(
                new ApiException(
                        e.getMessage(),
                        e,
                        HttpStatus.BAD_REQUEST,
                        ZonedDateTime.now()
                ),
                HttpStatus.BAD_REQUEST
        );
    }
}
