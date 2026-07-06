package com.pramod.firstapi.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.pramod.firstapi.exception.StudentNotFoundException;
import com.pramod.firstapi.model.Student;
import com.pramod.firstapi.repository.StudentRepository;

@Service
public class StudentService {

    private static final Logger logger =
            LogManager.getLogger(StudentService.class);

    @Autowired
    StudentRepository repository;

    @CacheEvict(value = "students", allEntries = true)
    public Student saveStudent(Student student) {

        logger.info("Saving Student : {}", student.getName());

        Student savedStudent = repository.save(student);

        logger.info("Student Saved Successfully");

        return savedStudent;
    }

    @Cacheable("students")
    public List<Student> getStudent() {

        logger.info("Fetching Students From Database...");

        return repository.findAll();
    }

    public Student getStudentById(int id) {

        logger.info("Fetching Student By ID : {}", id);

        return repository.findById(id)
                .orElseThrow(() -> {

                    logger.error("Student Not Found : {}", id);

                    return new StudentNotFoundException(
                            "Student Not Found : " + id);
                });
    }

    @CacheEvict(value = "students", allEntries = true)
    public void deleteStudent(int id) {

        logger.info("Deleting Student : {}", id);

        repository.deleteById(id);

        logger.info("Student Deleted Successfully");
    }

    @CacheEvict(value = "students", allEntries = true)
    public Student updateStudent(Student student) {

        logger.info("Updating Student : {}", student.getId());

        Student updatedStudent = repository.save(student);

        logger.info("Student Updated Successfully");

        return updatedStudent;
    }

    public List<Student> getStudentByName(String name) {

        logger.info("Searching Student By Name : {}", name);

        return repository.findByName(name);
    }

    public List<Student> findByCourse(String course) {

        logger.info("Fetching Students By Course : {}", course);

        return repository.findByCourse(course);
    }
}