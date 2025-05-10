package com.payroll.repository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.payroll.model.Company;

public interface CompanyRepository extends MongoRepository<Company, String> {

    Optional<Company> findByName(String name);
    Optional<Company> findByCompanyId(String companyId);
    Optional<Company> findById(String id);
    boolean existsByName(String name);
    boolean existsById(String id);
    boolean existsByCompanyId(String companyId);
}

