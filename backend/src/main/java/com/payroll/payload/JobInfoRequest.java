package com.payroll.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobInfoRequest {

    @NotBlank(message = "Employee type is required")
    private String employeeType;

    @NotBlank(message = "Job level is required")
    private String jobLevel;

    @NotBlank(message = "Job title is required")
    private String jobTitle;
}
