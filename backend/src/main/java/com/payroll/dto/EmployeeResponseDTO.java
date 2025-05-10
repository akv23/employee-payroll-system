package com.payroll.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeResponseDTO {
    private String id;
    private String empId;
    private String email;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String nationalId;
    private AddressDTO address;
}
