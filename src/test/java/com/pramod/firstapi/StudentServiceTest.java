package com.pramod.firstapi;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.pramod.firstapi.model.Student;
import com.pramod.firstapi.repository.StudentRepository;
import com.pramod.firstapi.service.StudentService;


public class StudentServiceTest {

	@Mock
	StudentRepository repository;

	@InjectMocks
	StudentService service;

	public StudentServiceTest() {

		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetStudentById() {

		Student student = new Student();

		student.setId(1);

		student.setName("Pramod");

		student.setCourse("MCA");

		when(repository.findById(1))

				.thenReturn(Optional.of(student));

		Student result =

				service.getStudentById(1);

		assertEquals("Pramod", result.getName());
	}
}
