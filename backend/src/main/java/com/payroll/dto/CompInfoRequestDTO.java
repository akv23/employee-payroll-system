package com.payroll.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
public class CompInfoRequestDTO {

    @NotNull(message = "Compensation Info ID cannot be null")
    @NotBlank(message = "Compensation Info ID cannot be blank")
    private String compInfoId; // CompInfo ID

    @NotNull(message = "PayGroup ID cannot be null")
    @NotBlank(message = "PayGroup ID cannot be blank")
    private PayComponentDTO payComponent;

    @NotNull(message = "Payment Info cannot be null")
    @NotBlank(message = "Payment Info cannot be blank")
    private PaymentInfoDTO paymentInfo;
}
