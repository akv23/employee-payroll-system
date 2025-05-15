package com.payroll.service;

import com.payroll.model.Role;
import com.payroll.model.User;

import com.payroll.repository.UserRepository;
import com.payroll.config.JwtUtil;
import lombok.RequiredArgsConstructor;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    // Method to register a new user (admin)
    public User registerAdmin(String username, String password) {
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Admin with this email already exists");
        }

        User admin = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .roles(Collections.singleton(Role.ADMIN))
                .build();

        return userRepository.save(admin);
    }

    // Method to Login a user (admin)
    public String login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtUtil.generateToken(user.getUsername(),user.getRoles());
    }

}
