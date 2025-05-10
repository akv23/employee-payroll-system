package com.payroll.model;

import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "comp_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CompInfo {

    @Id
    private String id;

    @Indexed(unique = true) // Unique index for Employee ID, handle duplicate key exception in the service layer
    @Field("comp_info_id")
    private String compInfoId;  // Employee ID
    private PayComponent payComponent;  // Embedded PayComponent (Salary breakdown)
    private PaymentInfo paymentInfo;    // Embedded Payment Info (Bank details)
}
