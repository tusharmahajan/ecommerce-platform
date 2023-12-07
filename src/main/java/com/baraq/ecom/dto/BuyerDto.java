package com.baraq.ecom.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BuyerDto {

    private String name;

    @JsonProperty("address")
    private AddressDto addressDto;
}
