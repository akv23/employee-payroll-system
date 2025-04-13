package com.payroll.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "compensation_info")
public class CompensationInfo {
    @Id
    private String id;
    private String payType;
    private String frequency;
    private Double amount;
}
