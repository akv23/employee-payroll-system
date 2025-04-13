package com.payroll.model;

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
    private String companyName;
    private String division;
    private String department;
    private String location;
}
