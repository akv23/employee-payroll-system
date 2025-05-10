package com.payroll.controller;

import com.payroll.dto.CompInfoRequestDTO;
import com.payroll.service.CompInfoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/compinfo")
@RequiredArgsConstructor
public class CompInfoController {

    private final CompInfoService compInfoService;

    // Create a new compensation info
    @PostMapping
    public ResponseEntity<?> createCompInfo(@Valid @RequestBody CompInfoRequestDTO compRequest) {
        return ResponseEntity.ok(compInfoService.createCompInfo(compRequest));
    }

    // Get compensation info by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getCompInfo(@PathVariable String id) {
        return ResponseEntity.ok(compInfoService.getCompInfoById(id));
    }

    // Get all compensation info
    @GetMapping("/all")
    public ResponseEntity<?> getAllCompInfo() {
        return ResponseEntity.ok(compInfoService.getAllCompInfo());
    }

    //Update compensation info by ID
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCompInfo(@PathVariable String id, @Valid @RequestBody CompInfoRequestDTO compRequest) {
        return ResponseEntity.ok(compInfoService.updateCompInfo(id, compRequest));
    }

    // Delete compensation info by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCompInfo(@PathVariable String id) {
        compInfoService.deleteCompInfo(id);
        return ResponseEntity.ok("Compensation Info deleted successfully");
    }
}

