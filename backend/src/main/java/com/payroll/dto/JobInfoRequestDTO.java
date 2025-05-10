package com.payroll.dto;

import lombok.Data;

import java.util.Set;

import com.payroll.model.EmployeeStatus;
import com.payroll.model.JobLevel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


@Data
public class JobInfoRequestDTO {

    @NotBlank(message = "Job Info ID cannot be blank")
    private String jobInfoId;

    @NotBlank(message = "Employee ID cannot be blank")
    private String employeeId;

    @NotBlank(message = "Company ID cannot be blank")
    private String companyId;

    @NotEmpty(message = "Job Level must contain at least one value")
    private Set<JobLevel> jobLevel;

    @NotBlank(message = "Job title cannot be blank")
    private String jobTitle;

    @NotBlank(message = "Compensation Info ID cannot be blank")
    private String compInfoId;

    @NotNull(message = "Employee status cannot be null")
    private EmployeeStatus employeeStatus;
}
