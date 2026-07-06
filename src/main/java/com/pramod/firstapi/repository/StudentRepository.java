package com.pramod.firstapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.pramod.firstapi.model.Student;

@Repository
public interface StudentRepository 
		extends JpaRepository<Student,Integer>{
	List<Student> findByName(
	        String name);
	List<Student>
	findByCourse(
	        String course);
}
