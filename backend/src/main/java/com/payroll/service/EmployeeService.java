package com.payroll.service;

import com.payroll.model.*;
import com.payroll.payload.EmployeeRequest;
import com.payroll.repository.*;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final CompanyInfoRepository companyInfoRepository;
    private final CompensationInfoRepository compensationInfoRepository;
    private final JobInfoRepository jobInfoRepository;

    public Employee createOrUpdateEmployeeFromRequest(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        
        // Set fields from EmployeeRequest
        employee.setEmpId(employeeRequest.getEmpId());
        employee.setEmail(employeeRequest.getEmail());
        employee.setPassword(employeeRequest.getPassword());
        employee.setFirstName(employeeRequest.getFirstName());
        employee.setLastName(employeeRequest.getLastName());
        employee.setDob(employeeRequest.getDob());
        employee.setMobileNumber(employeeRequest.getMobileNumber());
        employee.setNationalId(employeeRequest.getNationalId());
        employee.setAddress(employeeRequest.getAddress());

        // Auto-create referenced entities if IDs are not provided
        if (employeeRequest.getCompanyInfoId() != null) {
            employee.setCompanyInfo(companyInfoRepository.findById(employeeRequest.getCompanyInfoId())
                .orElseThrow(() -> new RuntimeException("CompanyInfo not found")));
        } else {
            CompanyInfo companyInfo = new CompanyInfo(); // Create default or handle default data
            employee.setCompanyInfo(companyInfoRepository.save(companyInfo));
        }

        if (employeeRequest.getCompensationInfoId() != null) {
            employee.setCompensationInfo(compensationInfoRepository.findById(employeeRequest.getCompensationInfoId())
                .orElseThrow(() -> new RuntimeException("CompensationInfo not found")));
        } else {
            CompensationInfo compensationInfo = new CompensationInfo(); // Create default or handle default data
            employee.setCompensationInfo(compensationInfoRepository.save(compensationInfo));
        }

        if (employeeRequest.getJobInfoId() != null) {
            employee.setJobInfo(jobInfoRepository.findById(employeeRequest.getJobInfoId())
                .orElseThrow(() -> new RuntimeException("JobInfo not found")));
        } else {
            JobInfo jobInfo = new JobInfo(); // Create default or handle default data
            employee.setJobInfo(jobInfoRepository.save(jobInfo));
        }

        return employeeRepository.save(employee);
    }

    public void deleteEmployee(String empId) {
        employeeRepository.deleteByEmpId(empId);
    }

    public Optional<Employee> getEmployeeByEmpId(String empId) {
        return employeeRepository.findByEmpId(empId);
    }
}
