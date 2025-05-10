package com.payroll.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompanyResponseDTO {
    private String id;
    @NotNull
    private String companyId;
    private String companyName;
    @NotNull
    private AddressDTO address;
    private String contactEmail;
    private String contactPhone;


}
