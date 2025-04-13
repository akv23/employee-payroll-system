package com.payroll.controller;

// import com.payroll.model.CompanyInfo;
import com.payroll.payload.CompanyInfoRequest;
import com.payroll.service.CompanyInfoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/company")
@RequiredArgsConstructor
public class CompanyInfoController {

    private final CompanyInfoService companyInfoService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody CompanyInfoRequest request) {
        return ResponseEntity.ok(companyInfoService.createCompanyInfo(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable String id) {
        return companyInfoService.getById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @Valid @RequestBody CompanyInfoRequest request) {
        try {
            return ResponseEntity.ok(companyInfoService.updateCompanyInfo(id, request));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        companyInfoService.deleteCompanyInfo(id);
        return ResponseEntity.ok("CompanyInfo deleted successfully");
    }
}
