package com.pramod.firstapi.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pramod.firstapi.exception.EmployeeNotFoundException;
import com.pramod.firstapi.model.Employee;
import com.pramod.firstapi.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private static final Logger logger =
            LogManager.getLogger(EmployeeService.class);

    @Autowired
    EmployeeRepository repository;

    @CacheEvict(value = "employees", allEntries = true)
    public Employee saveEmployee(Employee employee) {

        logger.info("Saving Employee : {}", employee.getName());

        Employee savedEmployee = repository.save(employee);

        logger.info("Employee Saved Successfully");

        return savedEmployee;
    }

    @Cacheable("employees")
    public List<Employee> getEmployees() {

        logger.info("Fetching Employees From Database...");

        return repository.findAll();
    }

    public Employee getEmployeeById(int id) {

        logger.info("Fetching Employee By ID : {}", id);

        return repository.findById(id)
                .orElseThrow(() -> {

                    logger.error("Employee Not Found : {}", id);

                    return new EmployeeNotFoundException(
                            "Employee Not Found : " + id);
                });
    }

    @CacheEvict(value = "employees", allEntries = true)
    public void deleteEmployee(int id) {

        logger.info("Deleting Employee : {}", id);

        repository.deleteById(id);

        logger.info("Employee Deleted Successfully");
    }

    @CacheEvict(value = "employees", allEntries = true)
    public Employee updateEmployee(Employee employee) {

        logger.info("Updating Employee : {}", employee.getId());

        Employee updatedEmployee = repository.save(employee);

        logger.info("Employee Updated Successfully");

        return updatedEmployee;
    }

    public Page<Employee> getEmployeesPage(
            int page,
            int size) {

        logger.info("Fetching Employees Page : {} Size : {}", page, size);

        return repository.findAll(
                PageRequest.of(page, size));
    }

    public List<Employee> getEmployeesSorted() {

        logger.info("Fetching Employees Sorted By Name");

        return repository.findAll(
                Sort.by("name"));
    }

    public List<Employee> getEmployeeByName(
            String name) {

        logger.info("Searching Employee By Name : {}", name);

        return repository.findByName(name);
    }

    public List<Employee> getSalaryGreaterThan(
            double salary) {

        logger.info("Fetching Employees With Salary Greater Than : {}", salary);

        return repository.findBySalaryGreaterThan(salary);
    }

    @CacheEvict(value = "employees", allEntries = true)
    public void saveAllEmployees(
            List<Employee> employees) {

        logger.info("Importing {} Employees From Excel", employees.size());

        repository.saveAll(employees);

        logger.info("Employees Imported Successfully");
    }
}