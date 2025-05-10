package com.payroll.model;

import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    @Id
    private String id;

    @Indexed(unique = true)     // Unique index for employee ID, handle duplicate key exception in the service layer
    private String empId;

    @Field("first_name")
    private String firstName;

    @Field("last_name")
    private String lastName;

    @Indexed(unique = true)     // Unique index for email, handle duplicate key exception in the service layer
    @Field("email")
    private String email;

    private String mobileNumber;
    
    @Indexed(unique = true)     // Unique index for national ID, handle duplicate key exception in the service layer
    private String nationalId;    // 

    private Address address;       // Embedded Address
    private String companyId;       // Reference to Company
    private String jobLevelId;      // Reference to JobLevel
    private String payGroupId;      // Reference to PayGroup
}

