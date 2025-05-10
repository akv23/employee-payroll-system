package com.payroll.controller;


import com.payroll.dto.JobInfoRequestDTO;
import com.payroll.service.JobInfoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jobinfo")
@RequiredArgsConstructor
public class JobInfoController {

    private final JobInfoService jobInfoService;

    // Create JobInfo
    @PostMapping
    public ResponseEntity<?> createJobInfo(@Valid @RequestBody JobInfoRequestDTO jobRequest) {
        return ResponseEntity.ok(jobInfoService.createJobInfo(jobRequest));
    }

    // Get JobInfo by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getJobInfo(@PathVariable String id) {
        return ResponseEntity.ok(jobInfoService.getJobInfoById(id));
    }

    // Get all JobInfos
    @GetMapping("/all")
    public ResponseEntity<?> getAllJobInfos() {
        return ResponseEntity.ok(jobInfoService.getAllJobInfos());
    }

    // Update JobInfo
    @PutMapping("/{id}")
    public ResponseEntity<?> updateJobInfo(@PathVariable String id, @Valid @RequestBody JobInfoRequestDTO jobRequest) {
        return ResponseEntity.ok(jobInfoService.updateJobInfo(id, jobRequest));
    }

    // Delete JobInfo
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJobInfo(@PathVariable String id) {
        jobInfoService.deleteJobInfo(id);
        return ResponseEntity.ok("Job Info deleted successfully");
    }
}
