package com.payroll.repository;

import com.payroll.model.JobInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JobInfoRepository extends MongoRepository<JobInfo, String> {
    JobInfo findByJobTitle(String jobTitle); // Find by job title
    JobInfo findByJobLevel(String jobLevel); // Find by job level
    JobInfo findByEmployeeType(String employeeType); // Find by employee type
}
