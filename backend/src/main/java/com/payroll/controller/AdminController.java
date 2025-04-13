package com.payroll.controller;

import com.payroll.model.Employee;
import com.payroll.payload.RegisterRequest;
import com.payroll.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Admin-only Endpoint - Creates EMPLOYEE or ADMIN
    @PostMapping("/create")
    public ResponseEntity<?> createByAdmin(@RequestBody RegisterRequest request) {

        if (employeeRepository.findByEmpId(request.getEmpId()).isPresent()) {
            return ResponseEntity.badRequest().body("Employee ID already exists.");
        }

        if (employeeRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already exists.");
        }

        Employee newUser = new Employee();
        newUser.setEmpId(request.getEmpId());
        newUser.setFirstName(request.getFirstName());
        newUser.setLastName(request.getLastName());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        newUser.setRole("Admin"); // ADMIN or EMPLOYEE
        newUser.setActive(true);
        newUser.setStatus("ACTIVE");
        newUser.setPayType("SALARIED"); // default
        newUser.setSalary(0);
        newUser.setHourlyRate(0);

        employeeRepository.save(newUser);
        return ResponseEntity.ok("User created successfully by admin.");
    }

    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(@RequestBody Employee emp) {
        if (employeeRepository.findByEmpId(emp.getEmpId()).isPresent()) {
            return ResponseEntity.badRequest().body("Employee ID already exists.");
        }

        if (employeeRepository.findByEmail(emp.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already exists.");
        }
        emp.setActive(true);
        emp.setStatus("ACTIVE");
        employeeRepository.save(emp);
        return ResponseEntity.ok("Employee added successfully.");
    }

    @PutMapping("/terminate/{empId}")
    public Employee terminateEmployee(@PathVariable String empId) {
        Employee emp = employeeRepository.findByEmpId(empId).orElseThrow(() -> new RuntimeException("Employee not found with Employee Id: " + empId));
        emp.setActive(false);
        emp.setStatus("TERMINATED");
        return employeeRepository.save(emp);
    }

    @GetMapping("/employees")
    public List<Employee> listEmployees() {
        return employeeRepository.findAll();
    }
}
