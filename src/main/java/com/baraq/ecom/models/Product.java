package com.baraq.ecom.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product {

    @Id
    private final String id = UUID.randomUUID().toString();

    @Column(name = "name")
    private String name;

    @Column(name = "inventory")
    private int inventory;

    @Column(name = "price")
    private int price;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pick_up_address", referencedColumnName = "id")
    private Address address;

}
