package com.baraq.ecom.service;

import com.baraq.ecom.dto.AddressDto;
import com.baraq.ecom.dto.ProductDto;
import com.baraq.ecom.models.Address;
import com.baraq.ecom.models.Product;
import com.baraq.ecom.repository.AddressRepository;
import com.baraq.ecom.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AddressRepository addressRepository;

    public String storeProductDetails(ProductDto productDto) {
        try {

            AddressDto addressDto = productDto.getAddressDto();
            Address address = Address.builder()
                    .addressLine1(addressDto.getAddressLine1())
                    .pincode(addressDto.getPincode())
                    .city(addressDto.getCity()).build();

            Product product = Product.builder()
                    .name(productDto.getName())
                    .inventory(productDto.getInventory())
                    .price(productDto.getPrice())
                    .address(address).build();

            addressRepository.save(address);
            productRepository.save(product);
            return product.getId();
        }
        catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
