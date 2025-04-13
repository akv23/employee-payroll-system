package com.payroll.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyInfoRequest {

    @NotBlank(message = "Company name is required")
    private String companyName;

    @NotBlank(message = "Division is required")
    private String division;

    @NotBlank(message = "Department is required")
    private String department;

    @NotBlank(message = "Location is required")
    private String location;
}
