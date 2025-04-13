package com.payroll.controller;

import com.payroll.payload.CompensationInfoRequest;
import com.payroll.service.CompensationInfoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/compensation")
@RequiredArgsConstructor
public class CompensationInfoController {

    private final CompensationInfoService compensationInfoService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody CompensationInfoRequest request) {
        return ResponseEntity.ok(compensationInfoService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable String id) {
        return compensationInfoService.getById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @Valid @RequestBody CompensationInfoRequest request) {
        try {
            return ResponseEntity.ok(compensationInfoService.update(id, request));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        compensationInfoService.delete(id);
        return ResponseEntity.ok("CompensationInfo deleted successfully");
    }
}
