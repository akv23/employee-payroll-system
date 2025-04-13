package com.payroll.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompensationInfoRequest {

    @NotBlank(message = "Pay type is required")
    private String payType;

    @NotBlank(message = "Frequency is required")
    private String frequency;

    @NotNull(message = "Amount is required")
    private Double amount;
}
