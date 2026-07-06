package com.pramod.firstapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pramod.firstapi.model.Department;

public interface DepartmentRepository
        extends JpaRepository<Department,Integer> {

}