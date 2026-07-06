package com.pramod.firstapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pramod.firstapi.model.Department;
import com.pramod.firstapi.repository.DepartmentRepository;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository repository;

    public Department saveDepartment(
            Department department) {

        return repository.save(department);
    }

    public List<Department> getDepartments() {

        return repository.findAll();
    }
}