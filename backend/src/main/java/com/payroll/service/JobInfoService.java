package com.payroll.service;

import com.payroll.model.JobInfo;
import com.payroll.payload.JobInfoRequest;
import com.payroll.repository.JobInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobInfoService {

    private final JobInfoRepository jobInfoRepository;

    public JobInfo createJobInfo(JobInfoRequest request) {
        JobInfo jobInfo = new JobInfo(null, request.getEmployeeType(), request.getJobLevel(), request.getJobTitle());
        return jobInfoRepository.save(jobInfo);
    }

    public Optional<JobInfo> getById(String id) {
        return jobInfoRepository.findById(id);
    }

    public JobInfo updateJobInfo(String id, JobInfoRequest request) {
        return jobInfoRepository.findById(id).map(existing -> {
            existing.setEmployeeType(request.getEmployeeType());
            existing.setJobLevel(request.getJobLevel());
            existing.setJobTitle(request.getJobTitle());
            return jobInfoRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("JobInfo not found with id: " + id));
    }

    public void deleteJobInfo(String id) {
        jobInfoRepository.deleteById(id);
    }
}
