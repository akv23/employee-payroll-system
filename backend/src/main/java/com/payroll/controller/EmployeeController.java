package com.payroll.controller;

import com.payroll.model.Employee;
import com.payroll.payload.EmployeeRequest;
import com.payroll.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/create")
    public ResponseEntity<?> createEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {
        try {
            Employee saved = employeeService.createOrUpdateEmployeeFromRequest(employeeRequest);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating employee: " + e.getMessage());
        }
    }

    @GetMapping("/{empId}")
    public ResponseEntity<?> getEmployeeById(@PathVariable String empId) {
        return employeeService.getEmployeeByEmpId(empId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{empId}")
    public ResponseEntity<?> updateEmployee(@PathVariable String empId, @Valid @RequestBody EmployeeRequest employeeRequest) {
        try {
            Employee updated = employeeService.createOrUpdateEmployeeFromRequest(employeeRequest);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating employee: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{empId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable String empId) {
        try {
            employeeService.deleteEmployee(empId);
            return ResponseEntity.ok("Employee deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error deleting employee: " + e.getMessage());
        }
    }
}
