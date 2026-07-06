package com.pramod.firstapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.Operation;
import java.io.ByteArrayInputStream;
import org.springframework.web.multipart.MultipartFile;

import com.pramod.firstapi.excel.EmployeeExcelImporter;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import com.pramod.firstapi.excel.EmployeeExcelExporter;

import com.pramod.firstapi.dto.LoginRequest;
import com.pramod.firstapi.dto.LoginResponse;
import com.pramod.firstapi.model.Course;
import com.pramod.firstapi.model.Department;
import com.pramod.firstapi.model.Employee;
import com.pramod.firstapi.model.Product;
import com.pramod.firstapi.model.Student;
import com.pramod.firstapi.security.JwtUtil;
import com.pramod.firstapi.service.CourseService;
import com.pramod.firstapi.service.DepartmentService;
import com.pramod.firstapi.service.EmployeeService;
import com.pramod.firstapi.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class HelloController {

	@Autowired
	EmployeeService service;
	
	@Autowired
	StudentService stservice;
	
	@Autowired
	DepartmentService departmentService;
	
	@Autowired
	CourseService courseService;
	
    @GetMapping("/hello")
    public String hello() {

        return "Hello Pramod";
    }
    
    @GetMapping("/name")
    public String name() {

        return "My Name is Pramod";
    }
    @GetMapping("/city")
    public String city() {

        return "Hospet";
    }
    @GetMapping("/company")
    public String company() {
    	return "Capgemini";
    }
    @GetMapping("/role")
    public String role(){
    	return "Software Test Engineer";
    }
    @GetMapping("/skills")
    public String skills() {
    	return "Java,Selenium,SQL";
    }
    @GetMapping("/expirence")
    public String Expirence() {
    	return "3 Years";
    }
    @PostMapping("/employee")
    public Employee createEmployee(
            @RequestBody Employee employee) {

        return employee;
    }
    @PostMapping("/welcome")
    public String welcome() {
    	return "Welcome to Spring Boot";
    }
    @PostMapping("/student")
    public Student createStudent(
    		@RequestBody Student student) {
    	return student;
    }
    
    @PostMapping("/product")
    public Product product(
    		@RequestBody Product product) {
    	return product;
    }
    @Operation(summary = "Save Employee")
    @PostMapping("/saveEmployee")
    public Employee saveEmployee(
            @RequestBody Employee employee) {

        return service.saveEmployee(employee);
    }
    @Operation(summary = "Get All Employees")
    @GetMapping("/employees")
    public List<Employee> getEmployees() {

        return service.getEmployees();
    }
    @Operation(summary = "Save Student")
    @PostMapping("/saveStudent")
    public Student saveStudent(
            @RequestBody Student student) {

        return stservice.saveStudent(student);
    }
    @Operation(summary = "Get All Students")
    @GetMapping("/students")
    public List<Student> getStudent() {

        return stservice.getStudent();
    }
    @Operation(summary = "Get Employee By ID")
    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee>
    getEmployeeById(
            @PathVariable int id) {

        Employee employee =
                service.getEmployeeById(id);

        if(employee != null) {

            return ResponseEntity.ok(employee);
        }

        return ResponseEntity.notFound().build();
    }
    @Operation(summary = "Update Employee")
    @PutMapping("/employee")
    public Employee updateEmployee(
            @RequestBody Employee employee) {

        return service.updateEmployee(employee);
    }
    
    @Operation(summary = "Delete Employee")
    @DeleteMapping("/employee/{id}")
    public String deleteEmployee(
            @PathVariable int id) {

        service.deleteEmployee(id);

        return "Employee Deleted Successfully";
    }
    @Operation(summary = "Get Student By ID")
    @GetMapping("/student/{id}")
    public ResponseEntity<Student>
    getStudentById(
            @PathVariable int id) {

        Student student =
                stservice.getStudentById(id);

        if(student != null) {

            return ResponseEntity.ok(student);
        }

        return ResponseEntity.notFound().build();
    }
    @Operation(summary = "Update Student")
    @PutMapping("/student")
    public Student updateStudent(
            @RequestBody Student student) {

        return stservice.updateStudent(student);
    }
    @Operation(summary = "Delete Student")
    @DeleteMapping("/student/{id}")
    public String deleteStudent(
            @PathVariable int id) {

        stservice.deleteStudent(id);

        return "Student Deleted Successfully";
    }
    @Operation(summary = "Save Department")
    @PostMapping("/saveDepartment")
    public Department saveDepartment(
            @RequestBody Department department) {

        return departmentService
                .saveDepartment(department);
    }
    @Operation(summary = "Get All Departments")
    @GetMapping("/departments")
    public List<Department> getDepartments() {

        return departmentService
                .getDepartments();
    }
    @Operation(summary = "Save Course")
    @PostMapping("/saveCourse")
    public Course saveCourse(
            @RequestBody Course course) {

        return courseService.saveCourse(course);
    }
    @Operation(summary = "Get All Courses")
    @GetMapping("/courses")
    public List<Course> getCourses() {

        return courseService.getCourses();
    }
    @GetMapping("/employees/page")
    public Page<Employee> getEmployeesPage(

            @RequestParam int page,

            @RequestParam int size) {

        return service.getEmployeesPage(
                page,
                size);
    }
    @GetMapping("/employees/sort")
    public List<Employee> getEmployeesSorted() {

        return service.getEmployeesSorted();
    }
    @GetMapping("/employee/name")
    public List<Employee>
    getEmployeeByName(

            @RequestParam String name) {

        return service.getEmployeeByName(
                name);
    }
    @GetMapping("/employee/salary")
    public List<Employee>
    getSalaryGreaterThan(

            @RequestParam double salary) {

        return service
                .getSalaryGreaterThan(
                        salary);
    }
    @GetMapping("/student/name")
    public List<Student>
    getStudentByName(
    		@RequestParam String name){
    	return stservice.getStudentByName(name);
    }
    @GetMapping("/student/course")
    public List<Student>
    findByCourse(
    		@RequestParam String course){
    	return stservice.findByCourse(course);
    }
    @GetMapping("/token")
    public String generateToken() {

        return JwtUtil.generateToken(
                "pramod");
    }
    @GetMapping("/username")
    public String username() {

        String token =
                JwtUtil.generateToken(
                        "pramod");

        return JwtUtil.extractUsername(
                token);
    }
    @PostMapping("/loginJwt")
    public LoginResponse loginJwt(
            @RequestBody
            LoginRequest request) {
    	

        if ("pramod".equals(
                request.getUsername())

                &&

                "12345".equals(
                        request.getPassword())) {

        	LoginResponse response =
        	        new LoginResponse();

        	response.setToken(
        	        JwtUtil.generateToken(
        	                request.getUsername()));

        	response.setMessage(
        	        "Login Successful");

        	return response;
        }

        LoginResponse response =
                new LoginResponse();

        response.setMessage(
                "Invalid Username or Password");

        return response;
    }
    @GetMapping("/validateToken")
    public String validateToken(
            @RequestParam String token) {

        String username =
                JwtUtil.extractUsername(token);

        return "Valid Token for User : "
                + username;
    }
    @GetMapping("/employeeJwt")
    public String employeeJwt(
            @RequestParam String token) {

        String username =
                JwtUtil.extractUsername(
                        token);

        return "Welcome " + username;
    }
    
    @GetMapping("/studentJwt")
    public String studentJwt(
            @RequestParam String token) {

        String username =
                JwtUtil.extractUsername(
                        token);

        return "Student Access Granted  " + username;
    }
    		
    @GetMapping("/departmentJwt")
    public String departmentJwt(
            @RequestParam String token) {

        String username =
                JwtUtil.extractUsername(
                        token);

        return "Department Access Granted  " + username;
    }
    @GetMapping("/employees/export")
    public ResponseEntity<InputStreamResource> exportEmployees()
            throws Exception {

        ByteArrayInputStream in =
                EmployeeExcelExporter.export(
                        service.getEmployees());

        HttpHeaders headers = new HttpHeaders();

        headers.add(
                "Content-Disposition",
                "attachment; filename=employees.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(
                        MediaType.parseMediaType(
                                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(new InputStreamResource(in));
    }
    		
    @PostMapping("/employees/import")
    public String importEmployees(
            @RequestParam("file")
            MultipartFile file)
            throws Exception {

        List<Employee> employees =
                EmployeeExcelImporter
                        .importEmployees(file);

        service.saveAllEmployees(
                employees);

        return "Employees Imported Successfully";
    }
}

