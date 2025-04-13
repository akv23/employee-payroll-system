package com.payroll.controller;

import com.payroll.model.Employee;
import com.payroll.payload.LoginRequest;
import com.payroll.payload.LoginResponse;
import com.payroll.repository.EmployeeRepository;
import com.payroll.security.JwtUtil;
import com.payroll.service.UserService;
import com.payroll.payload.RegisterRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Optional<Employee> optionalUser = employeeRepository.findByEmail(request.getEmail());

        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(401).body("Invalid email or password");
        }

        Employee user = optionalUser.get();

        if (!BCrypt.checkpw(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());

        return ResponseEntity.ok(new LoginResponse(token));
    }

    // Public Register Endpoint - Forces EMPLOYEE role
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {

        // Validate mandatory EMPLOYEE fields
        if (request.getFirstName() == null || request.getFirstName().isBlank() ||
                request.getLastName() == null || request.getLastName().isBlank()) {
            return ResponseEntity.badRequest().body("First name and last name are required for employees.");
        }

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
        newUser.setRole("EMPLOYEE");
        newUser.setActive(true);
        newUser.setStatus("ACTIVE");
        newUser.setPayType("SALARIED"); // default
        newUser.setSalary(0);
        newUser.setHourlyRate(0);

        employeeRepository.save(newUser);
        return ResponseEntity.ok("User registered successfully.");
    }

}
