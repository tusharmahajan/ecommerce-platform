package com.baraq.ecom.dto;

import com.baraq.ecom.models.PaymentMode;
import lombok.Data;

@Data
public class OrderDto {

    private String buyerId;
    private String productId;
    private int quantity;
    private PaymentMode paymentMode;
}
