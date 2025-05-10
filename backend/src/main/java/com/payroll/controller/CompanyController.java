package com.payroll.controller;


import com.payroll.dto.CompanyRequestDTO;
import com.payroll.dto.CompanyResponseDTO;
import com.payroll.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    // Create a new company
    @PostMapping
    public ResponseEntity<?> createCompany(@Valid @RequestBody CompanyRequestDTO companyRequest) {
        return ResponseEntity.ok(companyService.createCompany(companyRequest));
    }

    // Get a company by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getCompany(@PathVariable String id) {
        return ResponseEntity.ok(companyService.getCompanyById(id));
    }

    // Get all companies
    @GetMapping("/companies")
    public ResponseEntity<List<CompanyResponseDTO>> getAllCompanies() {
        List<CompanyResponseDTO> companies = companyService.getAllCompanies();
        return ResponseEntity.ok(companies);
    }

    // Update a company by ID
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCompany(@PathVariable String id, @Valid @RequestBody CompanyRequestDTO companyRequest) {
        return ResponseEntity.ok(companyService.updateCompany(id, companyRequest));
    }

    // Delete a company by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable String id) {
        companyService.deleteCompanyById(id);
        return ResponseEntity.ok("Company deleted successfully");
    }
}

