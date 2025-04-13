package com.payroll.service;

import com.payroll.model.Employee;
import com.payroll.model.SalarySlip;
import com.payroll.repository.EmployeeRepository;
import com.payroll.repository.SalarySlipRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PayrollService {

    @Autowired
    private EmployeeRepository repo;

    @Autowired
    private SalarySlipRepository slipRepo;

    
    public SalarySlip generateSalarySlip(Employee emp, double hoursWorked) {
        double gross;
        if ("SALARIED".equals(emp.getPayType())) {
            gross = emp.getSalary();
        } else {
            gross = emp.getHourlyRate() * hoursWorked;
        }

        double tax = calculateTax(gross);
        double netPay = gross - tax;

        SalarySlip slip = new SalarySlip();
        slip.setEmployeeId(emp.getEmpId());
        slip.setName(emp.getName());
        slip.setGrossPay(gross);
        slip.setTax(tax);
        slip.setNetPay(netPay);
        slip.setGeneratedDate(LocalDate.now());
        slipRepo.save(slip);
        return slip;
    }

    private double calculateTax(double income) {
        if (income <= 250000) return 0;
        else if (income <= 500000) return income * 0.05;
        else if (income <= 1000000) return income * 0.2;
        else return income * 0.3;
    }

    public SalarySlip generateSlip(String employeeId) {
        Optional<Employee> optional = repo.findByEmpId(employeeId);
        if (optional.isPresent()) {
            Employee emp = optional.get();
            double hoursWorked = 160; // default to full month for salaried
            return generateSalarySlip(emp, hoursWorked);
        }
        return null;
    }

    public List<SalarySlip> getSlipHistory(String employeeId) {
    return slipRepo.findByEmployeeId(employeeId);
    }

}
