package com.example.online_learning_platform.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.online_learning_platform.domain.Course;
import com.example.online_learning_platform.domain.Enrollment;
import com.example.online_learning_platform.domain.EEnrollmentStatus;
import com.example.online_learning_platform.domain.User;
import com.example.online_learning_platform.repository.CourseRepository;
import com.example.online_learning_platform.repository.EnrollmentRepository;
import com.example.online_learning_platform.repository.UserRepository;

@Service
public class EnrollmentService {
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    public String saveEnrollment(String userId, String courseId) {

        User user = userRepository.findById(UUID.fromString(userId)).orElse(null);
        if (user == null) {
            return "User not found.";
        }

        Course course = courseRepository.findById(UUID.fromString(courseId)).orElse(null);
        if (course == null) {
            return "Course not found.";
        }

        Boolean checkEnrollment = enrollmentRepository.existsByUserIdAndCourseId(
                UUID.fromString(userId), UUID.fromString(courseId));
        if (checkEnrollment) {
            return "User is already enrolled in this course.";
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setUser(user);
        enrollment.setCourse(course);
        enrollment.setEnrolledAt(LocalDateTime.now());
        enrollment.setStatus(EEnrollmentStatus.ACTIVE);

        enrollmentRepository.save(enrollment);
        return "Enrollment saved successfully.";
    }

    public List<Enrollment> getEnrollmentsByUser(String userId) {
        List<Enrollment> enrollments = enrollmentRepository.findByUserId(UUID.fromString(userId));
        if (!enrollments.isEmpty()) {
            return enrollments;
        } else {
            return null;
        }
    }
}
