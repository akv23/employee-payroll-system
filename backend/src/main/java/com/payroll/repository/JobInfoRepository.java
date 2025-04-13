package com.payroll.repository;

import com.payroll.model.JobInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JobInfoRepository extends MongoRepository<JobInfo, String> {}
