package com.payroll.controller;

import com.payroll.dto.EmployeeRequestDTO;
import com.payroll.model.Employee;
import com.payroll.model.User;
import com.payroll.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        String token = authService.login(username, password);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register-admin")
    public ResponseEntity<User> registerAdmin(@RequestParam String username, @RequestParam String password) {
        User admin = authService.registerAdmin(username, password);
        return ResponseEntity.ok(admin);
    }

    @PostMapping("/register-employee")
    public ResponseEntity<Employee> registerEmployee(@RequestBody EmployeeRequestDTO request) {
        Employee employee = authService.registerEmployee(request);
        return ResponseEntity.ok(employee);
    }
}
