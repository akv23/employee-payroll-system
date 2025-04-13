package com.payroll.repository;

import com.payroll.model.SalarySlip;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SalarySlipRepository extends MongoRepository<SalarySlip, String> {
    List<SalarySlip> findByEmployeeId(String employeeId);
}
