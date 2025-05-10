package com.payroll.service;


import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.payroll.dto.CompInfoRequestDTO;
import com.payroll.dto.CompInfoResponseDTO;
import com.payroll.dto.PayComponentDTO;
import com.payroll.dto.PaymentInfoDTO;
import com.payroll.model.CompInfo;
import com.payroll.model.PayComponent;
import com.payroll.model.PaymentInfo;
import com.payroll.repository.CompInfoRepository;



@Service
@RequiredArgsConstructor
public class CompInfoService {

    private final CompInfoRepository compInfoRepository;

    // Create a new compensation info
    public CompInfoResponseDTO createCompInfo(CompInfoRequestDTO requestCompInfo) {
        if(compInfoRepository.existsById(requestCompInfo.getCompInfoId())) {
            throw new RuntimeException("Compensation Info ID already exists");
        }
        CompInfo savedCompInfo = mapToEntity(requestCompInfo);
        savedCompInfo = compInfoRepository.save(savedCompInfo);
        return mapToResponseDTO(savedCompInfo);
    }

    // Get compensation info by ID
    public CompInfoResponseDTO getCompInfoById(String id) {
        CompInfo compInfo = compInfoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compensation Info not found with id: " + id));
        return mapToResponseDTO(compInfo);
    }

    //Get all compensation info
    public List<CompInfoResponseDTO> getAllCompInfo() {
        List<CompInfo> compInfos = compInfoRepository.findAll();
        if (compInfos.isEmpty()) {
            throw new RuntimeException("No compensation info found");
        }
        return compInfos.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    //Update compensation info
    public CompInfoResponseDTO updateCompInfo(String id, CompInfoRequestDTO requestCompInfo) {
        CompInfo existingCompInfo = compInfoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compensation Info not found with id: " + id));
        existingCompInfo.setPayComponent(mapToPayComponent(requestCompInfo.getPayComponent()));
        existingCompInfo.setPaymentInfo(mapToPaymentInfo(requestCompInfo.getPaymentInfo()));
        CompInfo updatedCompInfo = compInfoRepository.save(existingCompInfo);
        return mapToResponseDTO(updatedCompInfo);
    }

    //Delete compensation info
    public void deleteCompInfo(String id) {
        if(!compInfoRepository.existsById(id)) {
            throw new RuntimeException("Compensation Info not found with id: " + id);
        }
        compInfoRepository.deleteById(id);
    }

    //Helper method to map CompInfoRequestDTO to CompInfo
    private CompInfo mapToEntity(CompInfoRequestDTO requestCompInfo) {
        return CompInfo.builder()
                .compInfoId(requestCompInfo.getCompInfoId())
                .payComponent(mapToPayComponent(requestCompInfo.getPayComponent()))
                .paymentInfo(mapToPaymentInfo(requestCompInfo.getPaymentInfo()))
                .build();
    }

    //Helper menthod to map PayComponentDTO to PayComponent
    private PayComponent mapToPayComponent(PayComponentDTO payComponentDTO) {
        if (payComponentDTO == null) return null;
        return PayComponent.builder()
                .name(payComponentDTO.getName())
                .amount(payComponentDTO.getAmount())
                .frequency(payComponentDTO.getFrequency())
                .build();
    }

    //Helper menthod to map PaymentInfoDTO to PaymentInfo
    private PaymentInfo mapToPaymentInfo(PaymentInfoDTO paymentInfoDTO) {
        if (paymentInfoDTO == null) return null;
        return PaymentInfo.builder()
                .bankName(paymentInfoDTO.getBankName())
                .accountNumber(paymentInfoDTO.getAccountNumber())
                .ifscCode(paymentInfoDTO.getIfscCode())
                .accountHolderName(paymentInfoDTO.getAccountHolderName())
                .build();
    }

    //Helper method to map CompInfo to CompInfoResponseDTO
    private CompInfoResponseDTO mapToResponseDTO(CompInfo compInfo) {
        PayComponent payComponent = compInfo.getPayComponent();
        PaymentInfo paymentInfo = compInfo.getPaymentInfo();
        PayComponentDTO payComponentDTO = null;
        PaymentInfoDTO paymentInfoDTO = null;

        if(payComponent != null) {
            payComponentDTO = PayComponentDTO.builder()
                    .name(payComponent.getName())
                    .amount(payComponent.getAmount())
                    .frequency(payComponent.getFrequency())
                    .build();
        }
        if(paymentInfo != null) {
            paymentInfoDTO = PaymentInfoDTO.builder()
                    .bankName(paymentInfo.getBankName())
                    .accountNumber(paymentInfo.getAccountNumber())
                    .ifscCode(paymentInfo.getIfscCode())
                    .accountHolderName(paymentInfo.getAccountHolderName())
                    .build();
        }
        return CompInfoResponseDTO.builder()
                .compInfoId(compInfo.getCompInfoId())
                .payComponent(payComponentDTO)
                .paymentInfo(paymentInfoDTO)
                .build();
    }
}

