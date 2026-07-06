package com.pramod.firstapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pramod.firstapi.model.Course;
import com.pramod.firstapi.repository.CourseRepository;

@Service
public class CourseService {

    @Autowired
    CourseRepository repository;

    public Course saveCourse(
            Course course) {

        return repository.save(course);
    }

    public List<Course> getCourses() {

        return repository.findAll();
    }
}