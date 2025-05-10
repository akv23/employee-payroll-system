package com.payroll.repository;

import com.payroll.model.Employee;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
    Optional<Employee> findByEmpId(String empId);
    Optional<Employee> findByEmail(String email);
    Optional<Employee> findByNationalId(String nationalId);
    boolean existsByEmpId(String empId);
    boolean existsByEmail(String email);
    boolean existsByNationalId(String nationalId);
}
