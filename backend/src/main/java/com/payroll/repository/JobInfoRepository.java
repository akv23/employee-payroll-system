package com.payroll.repository;

import com.payroll.model.JobInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface JobInfoRepository extends MongoRepository<JobInfo, String> {
    Optional<JobInfo> findByEmployeeId(String employeeId);
    Optional<JobInfo> findByJobInfoId(String jobInfoId);
    Boolean existsByJobInfoId(String jobInfoId);
    Boolean existsByEmployeeId(String employeeId);
    
}
