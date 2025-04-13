package com.payroll.controller;

// import com.payroll.model.JobInfo;
import com.payroll.payload.JobInfoRequest;
import com.payroll.service.JobInfoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/job")
@RequiredArgsConstructor
public class JobInfoController {

    private final JobInfoService jobInfoService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody JobInfoRequest request) {
        return ResponseEntity.ok(jobInfoService.createJobInfo(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable String id) {
        return jobInfoService.getById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @Valid @RequestBody JobInfoRequest request) {
        try {
            return ResponseEntity.ok(jobInfoService.updateJobInfo(id, request));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        jobInfoService.deleteJobInfo(id);
        return ResponseEntity.ok("JobInfo deleted successfully");
    }
}
