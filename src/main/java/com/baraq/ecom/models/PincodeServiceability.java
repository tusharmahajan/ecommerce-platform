package com.baraq.ecom.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pincode_serviceability")
public class PincodeServiceability {

    @Id
    private final String id = UUID.randomUUID().toString();

    @ManyToOne
    @JoinColumn(name = "source_pincode", referencedColumnName = "pincode")
    private Address sourcePincode;

    @ManyToOne
    @JoinColumn(name = "destination_pincode", referencedColumnName = "pincode")
    private Address destinationPincode;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_mode")
    private PaymentMode paymentMode;
}
