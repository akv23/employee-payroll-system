package com.payroll.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document("salary_slips")
public class SalarySlip {
    @Id
    private String id;
    private String employeeId;
    private String name;
    private double grossPay;
    private double tax;
    private double netPay;
    private LocalDate generatedDate;

    // Getters and Setters
    public String getId() {
        return id;
    }
    public String getEmployeeId() {
        return employeeId;
    }
    public String getName() {
        return name;
    }
    public double getGrossPay() {
        return grossPay;
    }
    public double getTax() {
        return tax;
    }
    public double getNetPay() {
        return netPay;
    }
    public LocalDate getGeneratedDate() {
        return generatedDate;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setGrossPay(double grossPay) {
        this.grossPay = grossPay;
    }
    public void setTax(double tax) {
        this.tax = tax;
    }
    public void setNetPay(double netPay) {
        this.netPay = netPay;
    }
    public void setGeneratedDate(LocalDate generatedDate) {
        this.generatedDate = generatedDate;
    }
    
}
