package com.payroll.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "job_info")
public class JobInfo {

    @Id
    private String id;

    @NotBlank(message = "Employee type is required")
    private String employeeType; // Full-Time, Part-Time, etc.

    @NotBlank(message = "Job level is required")
    private String jobLevel; // L1, L2, etc.

    @NotBlank(message = "Job title is required")
    private String jobTitle;
}
