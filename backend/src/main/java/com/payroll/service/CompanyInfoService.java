package com.payroll.service;

import com.payroll.model.CompanyInfo;
import com.payroll.payload.CompanyInfoRequest;
import com.payroll.repository.CompanyInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyInfoService {

    private final CompanyInfoRepository companyInfoRepository;

    public CompanyInfo createCompanyInfo(CompanyInfoRequest request) {
        CompanyInfo companyInfo = new CompanyInfo();
        companyInfo.setCompanyName(request.getCompanyName());
        companyInfo.setDivision(request.getDivision());
        companyInfo.setDepartment(request.getDepartment());
        companyInfo.setLocation(request.getLocation());

        return companyInfoRepository.save(companyInfo);
    }

    public Optional<CompanyInfo> getById(String id) {
        return companyInfoRepository.findById(id);
    }

    public CompanyInfo updateCompanyInfo(String id, CompanyInfoRequest request) {
        return companyInfoRepository.findById(id).map(existing -> {
            existing.setCompanyName(request.getCompanyName());
            existing.setDivision(request.getDivision());
            existing.setDepartment(request.getDepartment());
            existing.setLocation(request.getLocation());
            return companyInfoRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("CompanyInfo not found with id: " + id));
    }

    public void deleteCompanyInfo(String id) {
        companyInfoRepository.deleteById(id);
    }
}
