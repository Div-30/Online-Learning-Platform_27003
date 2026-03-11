package com.example.online_learning_platform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.online_learning_platform.domain.Enrollment;
import com.example.online_learning_platform.service.EnrollmentService;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveEnrollment(@RequestParam String userId, @RequestParam String courseId) {

        String response = enrollmentService.saveEnrollment(userId, courseId);

        if (response.equals("Enrollment saved successfully.")) {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "/searchByUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getEnrollmentsByUser(@RequestParam String userId) {

        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByUser(userId);

        if (enrollments != null) {
            return new ResponseEntity<>(enrollments, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No enrollments found for this user.", HttpStatus.NOT_FOUND);
        }
    }
}
