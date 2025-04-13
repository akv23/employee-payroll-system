package com.payroll.controller;

import com.payroll.model.SalarySlip;
import com.payroll.repository.SalarySlipRepository;
import com.payroll.repository.EmployeeRepository;
import com.payroll.service.PayrollService;
import com.payroll.model.Employee;
import com.payroll.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;


@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PayrollService payrollService;

    @Autowired
    private SalarySlipRepository slipRepository;

    @GetMapping("/profile")
    public ResponseEntity<Employee> getProfile(Authentication authentication) {
        String empId = authentication.getName();
        Employee employee = userService.getProfile(empId);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/salary_slip/{empId}")
    public SalarySlip getSalarySlip(@PathVariable String empId) {
        return payrollService.generateSlip(empId);
    }

    @GetMapping("/history/{empId}")
    public List<SalarySlip> getSalaryHistory(@PathVariable String empId) {
        return payrollService.getSlipHistory(empId);
    }
}

