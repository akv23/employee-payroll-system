package com.payroll.repository;

import com.payroll.model.CompInfo;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface CompInfoRepository extends MongoRepository<CompInfo, String> {
    Optional<CompInfo> findById(String id);
    boolean existsById(String id);
    boolean existsByCompInfoId(String compInfoId); // Check if Employee ID exists
    Optional<CompInfo> findByCompInfoId(String compInfoId); // Find by Employee ID
}
