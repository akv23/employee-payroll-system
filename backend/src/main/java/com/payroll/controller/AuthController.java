package com.payroll.controller;

import com.payroll.payload.LoginRequest;
import com.payroll.payload.LoginResponse;
import com.payroll.payload.RegisterRequest;
import com.payroll.service.AuthService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // POST: Login user and return JWT token
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        String token = authService.authenticateUser(request);
        return ResponseEntity.ok(new LoginResponse(token));
    }

    // POST: Register employee (default EMPLOYEE role)
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        authService.registerEmployee(request);
        return ResponseEntity.ok("Employee registered successfully.");
    }
}
