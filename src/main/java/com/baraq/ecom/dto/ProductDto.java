package com.baraq.ecom.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductDto {

    private String name;
    private int inventory;
    private int price;

    @JsonProperty("address")
    private AddressDto addressDto;
}
