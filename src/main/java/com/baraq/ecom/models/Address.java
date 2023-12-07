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
@Table(name = "address")
public class Address {

    @Id
    private final String id = UUID.randomUUID().toString();

    @Column(name = "addressLine1")
    private String addressLine1;

    @Column(name = "city")
    private String city;

    @Column(name = "pincode", unique = true)
    private String pincode;

}
