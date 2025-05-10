package com.payroll.dto;



import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Data
public class CompanyRequestDTO {
    
    @Pattern(regexp = "^CMP\\d{4}$", message = "Company ID must start with CMP followed by 4 digits")
    @NotBlank(message = "Company ID is required")
    @NotNull(message = "Company ID cannot be null")
    private String companyId;

    @NotBlank(message = "Company name is required")
    @NotNull(message = "Company name cannot be null")
    private String companyName;
    private AddressDTO address;
    private String contactEmail;
    private String contactPhone;

}
