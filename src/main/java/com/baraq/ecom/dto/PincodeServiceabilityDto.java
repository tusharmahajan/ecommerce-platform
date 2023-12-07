package com.baraq.ecom.dto;

import com.baraq.ecom.models.PaymentMode;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PincodeServiceabilityDto {

    @JsonProperty("source_pincode")
    private String sourcePincode;

    @JsonProperty("destination_pincode")
    private String destinationPincode;

    private PaymentMode paymentMode;
}
