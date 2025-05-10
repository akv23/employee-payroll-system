package com.payroll.service;

import com.mongodb.DuplicateKeyException;
import com.payroll.dto.AddressDTO;
import com.payroll.dto.CompanyRequestDTO;
import com.payroll.dto.CompanyResponseDTO;
import com.payroll.exception.CompanyNotFoundException;
import com.payroll.model.Address;
import com.payroll.model.Company;
import com.payroll.repository.CompanyRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;


    // Create a new company
    public CompanyResponseDTO createCompany(CompanyRequestDTO request) {
        if(companyRepository.existsById(request.getCompanyId())) {
            throw new RuntimeException("Company ID already exists");
        }
        if(companyRepository.existsByName(request.getCompanyName())) {
            throw new RuntimeException("Company name already exists");
        }
        Company company = mapToEntity(request);
        try {
            company = companyRepository.save(company);
        } catch (DuplicateKeyException e) {
            throw new RuntimeException("Duplicate key: Company ID or Company Name already exists");
        }
        return maptoResponseDTO(company);
    }

    // Get a company by ID
    public CompanyResponseDTO getCompanyById(String id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found with id: " + id));
        return maptoResponseDTO(company);
    }

    // Get all companies
    public List<CompanyResponseDTO> getAllCompanies() {
        try {
            List<Company> companies = companyRepository.findAll();
            
            if (companies.isEmpty()) {
                throw new CompanyNotFoundException("No companies found");
            }
    
            return companies.stream()
                    .map(this::maptoResponseDTO)
                    .collect(Collectors.toList());
    
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve companies: " + e.getMessage(), e);
        }
    }
    
    // Get deleted company by ID
    public void deleteCompanyById(String id) {
        if(!companyRepository.existsById(id)) {
            throw new RuntimeException("Company not found with id: " + id);
        }
        companyRepository.deleteById(id);
    }

    // Update a company
    public CompanyResponseDTO updateCompany(String id, CompanyRequestDTO companyRequest) {
        Company existingCompany = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found with id: " + id));
    
        existingCompany.setCompanyId(companyRequest.getCompanyId());       
        existingCompany.setName(companyRequest.getCompanyName());
        existingCompany.setContactEmail(companyRequest.getContactEmail());
        existingCompany.setContactPhone(companyRequest.getContactPhone());
        existingCompany.setAddress(mapToAddress(companyRequest.getAddress()));

        existingCompany = companyRepository.save(existingCompany);
        return maptoResponseDTO(existingCompany);

    }

    // Helper methods to map CompanyReqyestDTO to Company 
    private Company mapToEntity(CompanyRequestDTO request) {
        return Company.builder()
                .name(request.getCompanyName())
                .address(mapToAddress(request.getAddress()))
                .contactEmail(request.getContactEmail())
                .contactPhone(request.getContactPhone())
                .build();
    }

    // Helper method to map AddressDTO to Address entity
    private Address mapToAddress(AddressDTO addressDTO) {
        if (addressDTO == null) return null;
    
        return Address.builder()
                .street(addressDTO.getStreet())
                .city(addressDTO.getCity())
                .state(addressDTO.getState())
                .postalCode(addressDTO.getPostalCode())
                .country(addressDTO.getCountry())
                .build();
    }

    // Helper method to map Company entity to CompanyResponseDTO
    private CompanyResponseDTO maptoResponseDTO(Company company) {
        Address address = company.getAddress();
        AddressDTO addressDTO = null;

        if (address != null) {
            addressDTO = AddressDTO.builder()
                    .street(address.getStreet())
                    .city(address.getCity())
                    .state(address.getState())
                    .postalCode(address.getPostalCode())
                    .country(address.getCountry())
                    .build();
        }
        return CompanyResponseDTO.builder()
                .id(company.getId())
                .companyName(company.getName())
                .address(addressDTO)
                .contactEmail(company.getContactEmail())
                .contactPhone(company.getContactPhone())
                .build();
    }
}
