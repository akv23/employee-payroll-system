package com.payroll.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PayComponentDTO {
    
    @NotBlank(message = "Component ID cannot be blank")
    private String name;

    @NotBlank(message = "Amount cannot be blank")
    private double amount;

    @NotBlank(message = "Frequency cannot be blank")
    private String frequency; // e.g., "Monthly", "Biweekly", etc.
}
