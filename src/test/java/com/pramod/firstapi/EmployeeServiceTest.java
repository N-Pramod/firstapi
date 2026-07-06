package com.pramod.firstapi;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.pramod.firstapi.model.Employee;
import com.pramod.firstapi.repository.EmployeeRepository;
import com.pramod.firstapi.service.EmployeeService;

public class EmployeeServiceTest {

	@Mock
	EmployeeRepository repository;

	@InjectMocks
	EmployeeService service;

	public EmployeeServiceTest() {

		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetEmployeeById() {

		Employee employee = new Employee();

		employee.setId(1);

		employee.setName("Pramod");

		employee.setSalary(50000);

		when(repository.findById(1))

				.thenReturn(Optional.of(employee));

		Employee result =

				service.getEmployeeById(1);

		assertEquals("Pramod", result.getName());
	}

	@Test
	void testSaveEmployee() {

	    Employee employee = new Employee();

	    employee.setId(1);
	    employee.setName("Pramod");
	    employee.setSalary(50000);

	    when(repository.save(employee))
	            .thenReturn(employee);

	    Employee result =
	            service.saveEmployee(employee);

	    assertEquals(
	            "Pramod",
	            result.getName());

	    assertEquals(
	            50000,
	            result.getSalary());
	}
}
