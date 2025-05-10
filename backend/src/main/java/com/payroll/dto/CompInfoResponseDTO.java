package com.payroll.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompInfoResponseDTO {
    
    private String id;
    private String compInfoId;
    private PayComponentDTO payComponent;
    private PaymentInfoDTO paymentInfo;
}
