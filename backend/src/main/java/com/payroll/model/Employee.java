package com.payroll.model;

import java.time.LocalDate;

// import com.payroll.model.Address;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "employees")
public class Employee {

    @Id
    private String id;

    @Indexed(unique = true)
    @NotNull(message = "Employee ID cannot be null")
    private String empId;

    @Indexed(unique = true)
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Password cannot be null")
    private String password;

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

    // References
    @DBRef
    private CompanyInfo companyInfo;

    @DBRef
    private CompensationInfo compensationInfo;

    @DBRef
    private JobInfo jobInfo;
}
