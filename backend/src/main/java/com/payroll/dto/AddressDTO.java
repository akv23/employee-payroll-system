package com.payroll.dto;


import lombok.Builder;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@Builder
public class AddressDTO {

    @NotBlank(message = "Street cannot be blank")
    @NotNull(message = "Street cannot be null")
    private String street;

    @NotBlank(message = "City cannot be blank")
    @NotNull(message = "City cannot be null")
    private String city;

    @NotBlank(message = "State cannot be blank")
    @NotNull(message = "State cannot be null")
    private String state;

    @NotBlank(message = "Postal Code cannot be blank")
    @NotNull(message = "Postal Code cannot be null")
    private String postalCode;

    @NotBlank(message = "Country cannot be blank")
    @NotNull(message = "Country cannot be null")
    private String country;
}
