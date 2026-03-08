package com.example.online_learning_platform.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.online_learning_platform.domain.Category;
import com.example.online_learning_platform.domain.Course;
import com.example.online_learning_platform.repository.CategoryRepository;
import com.example.online_learning_platform.repository.CourseRepository;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public String saveCourse(Course course) {

        Boolean checkCourse = courseRepository.existsByTitle(course.getTitle());

        if (checkCourse) {
            return "Course with this title already exists.";
        }

        course.setCreatedAt(LocalDateTime.now());
        courseRepository.save(course);
        return "Course saved successfully.";
    }

    public String addCategoryToCourse(String courseId, String categoryId) {
        Course course = courseRepository.findById(UUID.fromString(courseId)).orElse(null);
        if (course == null) {
            return "Course not found.";
        }

        Category category = categoryRepository.findById(UUID.fromString(categoryId)).orElse(null);
        if (category == null) {
            return "Category not found.";
        }

        course.getCategories().add(category);
        courseRepository.save(course);
        return "Category added to course successfully.";
    }

    public Page<Course> getAllCoursesSorted(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        return courseRepository.findAll(pageable);
    }

    public Page<Course> getCoursesByInstructor(String instructor, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("title").ascending());
        return courseRepository.findByInstructor(instructor, pageable);
    }
}
