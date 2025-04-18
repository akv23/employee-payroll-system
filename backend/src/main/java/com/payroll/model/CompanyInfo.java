package com.payroll.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "company_info")
public class CompanyInfo {

    @Id
    private String id;

    @NotBlank(message = "Company name is required")
    private String companyName;

    @NotBlank(message = "Division is required")
    private String division;

    @NotBlank(message = "Department is required")
    private String department;

    @NotBlank(message = "Location is required")
    private String location;
}
