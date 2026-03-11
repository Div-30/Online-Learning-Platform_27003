package com.example.online_learning_platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.online_learning_platform.domain.Course;
import com.example.online_learning_platform.service.CourseService;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveCourse(@RequestBody Course course) {

        String response = courseService.saveCourse(course);

        if (response.equals("Course saved successfully.")) {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @PostMapping(value = "/addCategory", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addCategoryToCourse(@RequestParam String courseId, @RequestParam String categoryId) {

        String response = courseService.addCategoryToCourse(courseId, categoryId);

        if (response.equals("Category added to course successfully.")) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllCoursesSorted(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title") String sortBy) {

        Page<Course> courses = courseService.getAllCoursesSorted(page, size, sortBy);

        if (courses != null && !courses.isEmpty()) {
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No courses found.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/searchByInstructor", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCoursesByInstructor(
            @RequestParam String instructor,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Course> courses = courseService.getCoursesByInstructor(instructor, page, size);

        if (courses != null && !courses.isEmpty()) {
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No courses found for this instructor.", HttpStatus.NOT_FOUND);
        }
    }
}
