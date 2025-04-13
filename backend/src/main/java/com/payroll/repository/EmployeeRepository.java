package com.payroll.repository;

import com.payroll.model.Employee;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

    Optional<Employee> findByEmail(String email);
    Optional<Employee> findByEmpId(String empId);
    Optional<Employee> findByEmailAndEmpId(String email, String empId);

    void deleteByEmail(String email);
    void deleteByEmpId(String empId);
    void deleteByEmailAndEmpId(String email, String empId);
}
