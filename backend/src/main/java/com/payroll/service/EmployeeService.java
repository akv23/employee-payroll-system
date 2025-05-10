package com.payroll.service;

import com.mongodb.DuplicateKeyException;
import com.payroll.dto.AddressDTO;
import com.payroll.dto.EmployeeRequestDTO;
import com.payroll.dto.EmployeeResponseDTO;
import com.payroll.model.Address;
import com.payroll.model.Employee;
import com.payroll.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    // Create a new employee
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO requestEmployee) {
        if (employeeRepository.existsByEmpId(requestEmployee.getEmpId())) {
            throw new RuntimeException("Employee ID already exists");
        }
        if (employeeRepository.existsByEmail(requestEmployee.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        if (employeeRepository.existsByNationalId(requestEmployee.getNationalId())) {
            throw new RuntimeException("National ID already exists");
        }
        Employee employee = mapToEntity(requestEmployee);
        try {
            employee = employeeRepository.save(employee);
        } catch (DuplicateKeyException e) {
            throw new RuntimeException("Duplicate key: Employee ID or Email already exists");

        }
        return mapToResponseDTO(employee);
    }

    // Get employee by ID
    public EmployeeResponseDTO getEmployeeById(String id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        return mapToResponseDTO(employee);
    }

    //Get all employees
    public List<EmployeeResponseDTO> getAllEmployees() {
        try{
            List<Employee> employees = employeeRepository.findAll();
            if (employees.isEmpty()) {
                throw new RuntimeException("No employees found");
            }
            return employees.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error fetching employees: " + e.getMessage());
        }

    }

    //Delete employee by ID
    public void deleteEmployeeById(String id) {
       if(!employeeRepository.existsById(id)) {
            throw new RuntimeException("Employee not found with id: " + id);
        }
        employeeRepository.deleteById(id);
    }
    // Update employee by ID
    public EmployeeResponseDTO updateEmployee(String id, EmployeeRequestDTO requestEmployee) {
        Employee existingemployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        existingemployee.setFirstName(requestEmployee.getFirstName());
        existingemployee.setLastName(requestEmployee.getLastName());
        existingemployee.setEmail(requestEmployee.getEmail());
        existingemployee.setMobileNumber(requestEmployee.getMobileNumber());
        existingemployee.setNationalId(requestEmployee.getNationalId());
        existingemployee.setEmpId(requestEmployee.getEmpId());
        existingemployee.setAddress(mapToAddress(requestEmployee.getAddress()));

        existingemployee = employeeRepository.save(existingemployee);
        return mapToResponseDTO(existingemployee);
    }

    //Helper methods to map EmployeeRequestDTO to Employee
    private Employee mapToEntity(EmployeeRequestDTO requestEmployee) {
        return Employee.builder()
                .empId(requestEmployee.getEmpId())
                .firstName(requestEmployee.getFirstName())
                .lastName(requestEmployee.getLastName())
                .email(requestEmployee.getEmail())
                .mobileNumber(requestEmployee.getMobileNumber())
                .nationalId(requestEmployee.getNationalId())
                .address(mapToAddress(requestEmployee.getAddress()))
                .build();
    }

    //Helper methods to map AddressDTO to Address
     private Address mapToAddress(AddressDTO requestAddress) {
        if (requestAddress == null) return null;
        return Address.builder()
                .street(requestAddress.getStreet())
                .city(requestAddress.getCity())
                .state(requestAddress.getState())
                .postalCode(requestAddress.getPostalCode())
                .country(requestAddress.getCountry())
                .build();
    }

    //Helper method to map Employee to EmployeeResponseDTO
    private EmployeeResponseDTO mapToResponseDTO(Employee employee) {
        Address address = employee.getAddress();
        AddressDTO addressDTO = null;

        if (address != null) {
            addressDTO = AddressDTO.builder()
                    .street(address.getStreet())
                    .city(address.getCity())
                    .state(address.getState())
                    .postalCode(address.getPostalCode())
                    .country(address.getCountry())
                    .build();
        }

        return EmployeeResponseDTO.builder()
                .id(employee.getId())
                .empId(employee.getEmpId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .mobileNumber(employee.getMobileNumber())
                .nationalId(employee.getNationalId())
                .address(addressDTO)
                .build();
    }
}

