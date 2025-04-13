package com.payroll.payload;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    // Employee Info
    @NotNull(message = "Employee Info is required")
    @Valid
    private EmployeeRequest employee;

    // Company Info
    @NotNull(message = "Company Info is required")
    @Valid
    private CompanyInfoRequest companyInfo;

    // Job Info
    @NotNull(message = "Job Info is required")
    @Valid
    private JobInfoRequest jobInfo;

    // Compensation Info
    @NotNull(message = "Compensation Info is required")
    @Valid
    private CompensationInfoRequest compensationInfo;
}
