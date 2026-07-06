package com.pramod.firstapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pramod.firstapi.model.Course;

public interface CourseRepository
        extends JpaRepository<Course,Integer> {

}