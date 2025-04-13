package com.payroll.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "compensation_info")
public class CompensationInfo {

    @Id
    private String id;

    @NotBlank(message = "Pay type is required")
    private String payType; // Hourly, Salary

    @NotBlank(message = "Frequency is required")
    private String frequency; // Monthly, Bi-weekly, etc.

    @NotNull(message = "Amount is required")
    private Double amount;

    @DBRef
    private Employee employee;
}
