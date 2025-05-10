package com.payroll.service;

import com.payroll.dto.JobInfoRequestDTO;
import com.payroll.dto.JobInfoResponseDTO;
import com.payroll.exception.NoJobInfoFoundException;
import com.payroll.model.JobInfo;
import com.payroll.repository.JobInfoRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class JobInfoService {

    private final JobInfoRepository jobInfoRepository;

    // Create JobInfo
    public JobInfoResponseDTO createJobInfo(JobInfoRequestDTO dto) {
        if (jobInfoRepository.existsByJobInfoId(dto.getJobInfoId())) {
            throw new RuntimeException("JobInfo ID already exists");
        }

        JobInfo jobInfo = mapToEntity(dto);
        jobInfo = jobInfoRepository.save(jobInfo);
        return mapToResponseDTO(jobInfo);
    }

     // Get JobInfo by ID
     public JobInfoResponseDTO getJobInfoById(String id) {
        JobInfo jobInfo = jobInfoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("JobInfo not found with id: " + id));
        return mapToResponseDTO(jobInfo);
    }

     // Get all JobInfos
    public List<JobInfoResponseDTO> getAllJobInfos() {
        List<JobInfo> jobInfos = jobInfoRepository.findAll();
        if (jobInfos.isEmpty()) {
            throw new NoJobInfoFoundException("No Job Info records found.");
        }
        return jobInfos.stream()
            .map(this::mapToResponseDTO)
            .collect(Collectors.toList());
    }

    // Update JobInfo
    public JobInfoResponseDTO updateJobInfo(String id, JobInfoRequestDTO dto) {
        JobInfo jobInfo = jobInfoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("JobInfo not found with id: " + id));

        jobInfo.setJobInfoId(dto.getJobInfoId());
        jobInfo.setEmployeeId(dto.getEmployeeId());
        jobInfo.setCompanyId(dto.getCompanyId());
        jobInfo.setJobLevel(dto.getJobLevel());
        jobInfo.setJobTitle(dto.getJobTitle());
        jobInfo.setCompInfoId(dto.getCompInfoId());
        jobInfo.setEmployeeStatus(dto.getEmployeeStatus());

        jobInfo = jobInfoRepository.save(jobInfo);
        return mapToResponseDTO(jobInfo);
    }

    // Delete JobInfo by ID
    public void deleteJobInfo(String id) {
        if (!jobInfoRepository.existsById(id)) {
            throw new RuntimeException("JobInfo not found with id: " + id);
        }
        jobInfoRepository.deleteById(id);
    }
    // Helper method to JobInfoRequestDTO to JobInfo
    private JobInfo mapToEntity(JobInfoRequestDTO dto) {
        return JobInfo.builder()
                .jobInfoId(dto.getJobInfoId())
                .employeeId(dto.getEmployeeId())
                .companyId(dto.getCompanyId())
                .jobLevel(dto.getJobLevel())
                .jobTitle(dto.getJobTitle())
                .compInfoId(dto.getCompInfoId())
                .employeeStatus(dto.getEmployeeStatus())
                .build();
    }
    // Helper method to JobInfo to JobInfoResponseDTO\
    private JobInfoResponseDTO mapToResponseDTO(JobInfo jobInfo) {
        return JobInfoResponseDTO.builder()
                .id(jobInfo.getId())
                .jobInfoId(jobInfo.getJobInfoId())
                .employeeId(jobInfo.getEmployeeId())
                .companyId(jobInfo.getCompanyId())
                .jobLevel(jobInfo.getJobLevel())
                .jobTitle(jobInfo.getJobTitle())
                .compInfoId(jobInfo.getCompInfoId())
                .employeeStatus(jobInfo.getEmployeeStatus())
                .build();
    }
}
