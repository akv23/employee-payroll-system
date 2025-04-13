package com.payroll.repository;

import com.payroll.model.CompensationInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompensationInfoRepository extends MongoRepository<CompensationInfo, String> {
    CompensationInfo findByEmployeeId(String employeeId); // Find by employee ID
    CompensationInfo findByPayType(String payType); // Find by pay type
    CompensationInfo findByFrequency(String frequency); // Find by frequency
    CompensationInfo findByAmount(Double amount); // Find by amount
}
