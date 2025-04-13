package com.payroll.repository;

import com.payroll.model.CompanyInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompanyInfoRepository extends MongoRepository<CompanyInfo, String> {}
