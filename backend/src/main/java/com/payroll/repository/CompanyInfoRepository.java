package com.payroll.repository;

import com.payroll.model.CompanyInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompanyInfoRepository extends MongoRepository<CompanyInfo, String> {
    CompanyInfo findByCompanyName(String companyName);

    CompanyInfo findByDivision(String division);

    CompanyInfo findByDepartment(String department);

    CompanyInfo findByLocation(String location);

}
