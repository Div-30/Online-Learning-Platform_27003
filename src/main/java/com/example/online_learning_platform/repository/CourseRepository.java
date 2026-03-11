package com.example.online_learning_platform.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.online_learning_platform.domain.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {

    Boolean existsByTitle(String title);

    Page<Course> findAll(Pageable pageable);

    Page<Course> findByInstructor(String instructor, Pageable pageable);
}
