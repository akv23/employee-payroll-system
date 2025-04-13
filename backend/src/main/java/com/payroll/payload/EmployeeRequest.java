package com.payroll.payload;

import com.payroll.model.Address;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

import org.springframework.data.mongodb.core.index.Indexed;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {

    @Indexed(unique = true)
    @NotNull(message = "Employee ID cannot be null")
    private String empId;

    @Indexed(unique = true)
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Password cannot be null")
    private String password;

    @NotBlank(message = "Role is required")
    private String role; // ADMIN or EMPLOYEE

    @NotNull(message = "First Name cannot be null")
    private String firstName;

    @NotNull(message = "Last Name cannot be null")
    private String lastName;

    @NotNull(message = "Date of Birth cannot be null")
    private LocalDate dob;

    @NotNull(message = "Mobile Number cannot be null")
    @Pattern(regexp = "^\\d{10}$", message = "Mobile Number should be 10 digits")
    private String mobileNumber;

    @NotNull(message = "National ID cannot be null")
    private String nationalId;

    
    
    private Address address;

    private String companyInfoId;
    private String compensationInfoId;
    private String jobInfoId;
}
