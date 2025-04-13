package com.payroll.service;

import com.payroll.model.CompensationInfo;
import com.payroll.payload.CompensationInfoRequest;
import com.payroll.repository.CompensationInfoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class CompensationInfoService {

    private final CompensationInfoRepository compensationInfoRepository = null;

    public CompensationInfo create(CompensationInfoRequest request) {
        CompensationInfo info = CompensationInfo.builder()
            .payType(request.getPayType())
            .frequency(request.getFrequency())
            .amount(request.getAmount())
            .build();
        return compensationInfoRepository.save(info);
    }

    public Optional<CompensationInfo> getById(String id) {
        return compensationInfoRepository.findById(id);
    }

    public CompensationInfo update(String id, CompensationInfoRequest request) {
        return compensationInfoRepository.findById(id).map(existing -> {
            existing.setPayType(request.getPayType());
            existing.setFrequency(request.getFrequency());
            existing.setAmount(request.getAmount());
            return compensationInfoRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("CompensationInfo not found with id: " + id));
    }

    public void delete(String id) {
        compensationInfoRepository.deleteById(id);
    }
}
