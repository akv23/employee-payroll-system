package com.payroll.service;



import com.payroll.model.Role;
import com.payroll.model.User;
import com.payroll.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;

    public List<User> getAllAdmins() {
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getRoles() != null && user.getRoles().contains(Role.ADMIN))
                .collect(Collectors.toList());
    }

    public List<User> getAllSuperAdmins() {
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getRoles() != null && user.getRoles().contains(Role.SUPER_ADMIN))
                .collect(Collectors.toList());
    }

    public void deleteAdmin(String id) {
        userRepository.deleteById(id);
    }
}

