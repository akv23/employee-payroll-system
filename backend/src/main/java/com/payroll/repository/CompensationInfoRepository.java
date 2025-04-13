package com.payroll.repository;

import com.payroll.model.CompensationInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompensationInfoRepository extends MongoRepository<CompensationInfo, String> {}
