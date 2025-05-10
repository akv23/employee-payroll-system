package com.payroll.model;

import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "company")
@Data
@Builder
public class Company {
    @Id
    private String id;

    @Indexed(unique = true)     // Unique index for Company ID, handle duplicate key exception in the service layer
    private String companyId;

    private String name;
    private Address address;
    private String contactEmail;
    private String contactPhone;
}
