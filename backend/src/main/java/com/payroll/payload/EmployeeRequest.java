package com.payroll.payload;

import com.payroll.model.Address;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {
    private String empId;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String mobileNumber;
    private String nationalId;
    private Address address;

    private String companyInfoId;
    private String compensationInfoId;
    private String jobInfoId;
}
