package com.payroll.service;


import com.payroll.dto.AddressDTO;
import com.payroll.dto.EmployeeRequestDTO;
import com.payroll.model.Address;
import com.payroll.model.Employee;
import com.payroll.model.Role;
import com.payroll.model.User;
import com.payroll.repository.EmployeeRepository;
import com.payroll.repository.UserRepository;
import com.payroll.config.JwtUtil;
import lombok.RequiredArgsConstructor;

import java.util.Collections;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public String login(String username, String password) {
        User user = userRepository.findByUsername(username)
    .orElseThrow(() -> new RuntimeException("Invalid credentials"));
        
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        String role = user.getRoles().stream()
                      .findFirst()
                      .map(Enum::name)
                      .orElse("ADMIN");  // Default role if none is found
        
        return jwtUtil.generateToken(user.getUsername(), role);
    }

    public Employee registerEmployee(EmployeeRequestDTO request) {
        if (employeeRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Employee with this email already exists");
        }

        Employee employee = Employee.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .mobileNumber(request.getMobileNumber())
                .nationalId(request.getNationalId())
                .address(mapToAddress(request.getAddress()))
                .build();

        return employeeRepository.save(employee);
    }

    private Address mapToAddress(AddressDTO addressDTO) {
    if (addressDTO == null) return null;

    return Address.builder()
            .street(addressDTO.getStreet())
            .city(addressDTO.getCity())
            .state(addressDTO.getState())
            .postalCode(addressDTO.getPostalCode())
            .country(addressDTO.getCountry())
            .build();
}

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
}
