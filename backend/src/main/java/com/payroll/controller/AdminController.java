package com.payroll.controller;

import com.payroll.payload.RegisterRequest;
import com.payroll.service.AuthService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> registerAdmin(@Valid @RequestBody RegisterRequest request) {
        authService.registerAdmin(request);
        return ResponseEntity.ok("Admin registered successfully.");
    }
}

