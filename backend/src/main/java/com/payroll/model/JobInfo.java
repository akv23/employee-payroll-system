package com.payroll.model;

import lombok.*;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "job_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobInfo {

    @Id
    private String id;

    @Indexed(unique = true) // Unique index for job info ID, handle duplicate key errors in the service layer
    @Field("job_info_id")
    private String jobInfoId;
    
    @Field("employee_id")
    private String employeeId;          // Reference to Employee
    
    @Field("company_id")
    private String companyId;           // Reference to Company
    
    @Field("job_level")
    private Set<JobLevel> jobLevel;     // Enum for job levels (e.g., INTERN, ENTRY, MID, SENIOR, etc.)
    
    @Field("job_title")
    private String jobTitle;            // Job title of the employee   
   
   @Field("CompInfo_id")
    private String compInfoId;
    
    @Field("EmployeeStatus")
    private EmployeeStatus employeeStatus;  // Enum for employee status (e.g., ACTIVE, INACTIVE)
}

