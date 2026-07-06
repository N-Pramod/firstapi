package com.pramod.firstapi.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Department {

    @Id
    private int id;

    private String name;

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	@OneToMany
    @JoinColumn(name = "department_id")
    private List<Employee> employees;

    public Department() {
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    // Generate Getters Setters
}