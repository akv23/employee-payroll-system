package com.payroll.dto;


import java.util.Set;

import com.payroll.model.EmployeeStatus;
import com.payroll.model.JobLevel;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JobInfoResponseDTO {
   private String id;
    private String jobInfoId;
    private String employeeId;
    private String companyId;
    private Set<JobLevel> jobLevel;
    private String jobTitle;
    private String compInfoId;
    private EmployeeStatus employeeStatus;
}
