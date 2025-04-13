package com.payroll.service;

import com.payroll.payload.*;
import com.payroll.model.*;
import com.payroll.security.JwtUtil;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final EmployeeService employeeService;
    private final CompanyInfoService companyInfoService;
    private final JobInfoService jobInfoService;
    private final CompensationInfoService compensationInfoService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public String authenticateUser(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername(); // same as request.getEmail()

        // Extract first role from authorities
        String role = userDetails.getAuthorities().stream()
                .findFirst()
                .map(auth -> auth.getAuthority().replace("ROLE_", "")) // e.g., "ROLE_ADMIN" -> "ADMIN"
                .orElse("EMPLOYEE");

        return jwtUtil.generateToken(email, role);
    }

    public void registerEmployee(RegisterRequest request) {

        // Create associated entities
        CompanyInfo companyInfo = companyInfoService.createCompanyInfo(request.getCompanyInfo());
        JobInfo jobInfo = jobInfoService.createJobInfo(request.getJobInfo());
        CompensationInfo compensationInfo = compensationInfoService.create(request.getCompensationInfo());

        // request Ids
        EmployeeRequest employeeRequest = request.getEmployee();
        employeeRequest.setCompanyInfoId(companyInfo.getId());
        employeeRequest.setJobInfoId(jobInfo.getId());
        employeeRequest.setCompensationInfoId(compensationInfo.getId());

        // Create the employee (default role = EMPLOYEE)
        employeeService.createOrUpdateEmployeeFromRequest(employeeRequest);
        employeeRequest.setRole("EMPLOYEE");
    employeeService.createOrUpdateEmployeeFromRequest(employeeRequest);

    }

    public void registerAdmin(RegisterRequest request) {

        // Create associated entities
        CompanyInfo companyInfo = companyInfoService.createCompanyInfo(request.getCompanyInfo());
        JobInfo jobInfo = jobInfoService.createJobInfo(request.getJobInfo());
        CompensationInfo compensationInfo = compensationInfoService.create(request.getCompensationInfo());

        // request Ids
        EmployeeRequest employeeRequest = request.getEmployee();
        employeeRequest.setCompanyInfoId(companyInfo.getId());
        employeeRequest.setJobInfoId(jobInfo.getId());
        employeeRequest.setCompensationInfoId(compensationInfo.getId());

        // Create the employee ( role = Admin)
        employeeService.createOrUpdateEmployeeFromRequest(employeeRequest);
        employeeRequest.setRole("ADMIN");
        employeeService.createOrUpdateEmployeeFromRequest(employeeRequest);
    }
}
