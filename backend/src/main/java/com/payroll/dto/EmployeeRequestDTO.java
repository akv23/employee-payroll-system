package com.payroll.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmployeeRequestDTO {
   
    @NotBlank(message = "Employee ID cannot be blank")
    @Pattern(regexp = "^EMP\\d{4}$", message = "Employee ID must start with EMP followed by 4 digits")
    @NotNull(message = "Employee ID cannot be null")
    private String empId;

    @Email(message = "Email should be valid")
    @NotNull(message = "Email cannot be null")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotNull(message = "First Name cannot be null")
    @NotBlank(message = "First Name cannot be blank")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First Name should contain only letters")
    @Size(min = 2, max = 30, message = "First Name should be between 2 and 30 characters")
    private String firstName;

    @NotNull(message = "Last Name cannot be null")
    @NotBlank(message = "Last Name cannot be blank")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last Name should contain only letters")
    @Size(min = 2, max = 30, message = "Last Name should be between 2 and 30 characters")
    private String lastName;

    @NotNull(message = "Mobile Number cannot be null")
    @Pattern(regexp = "^\\d{10}$", message = "Mobile Number should be 10 digits")
    @NotBlank(message = "Mobile Number cannot be blank")
    private String mobileNumber;

    @NotNull(message = "National ID cannot be null")
    @NotBlank(message = "National ID cannot be blank")
    @Pattern(regexp = "^\\d{12}$", message = "National ID should be 12 digits")
    private String nationalId;

    private AddressDTO address;
}
