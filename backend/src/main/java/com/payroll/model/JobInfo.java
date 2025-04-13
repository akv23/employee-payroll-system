package com.payroll.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "job_info")
public class JobInfo {
    @Id
    private String id;
    private String employeeType;
    private String jobLevel;
    private String jobTitle;
}
