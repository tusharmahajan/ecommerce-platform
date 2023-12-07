package com.baraq.ecom.service;

import com.baraq.ecom.dto.AddressDto;
import com.baraq.ecom.dto.BuyerDto;
import com.baraq.ecom.models.Address;
import com.baraq.ecom.models.Buyer;
import com.baraq.ecom.repository.AddressRepository;
import com.baraq.ecom.repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyerService {

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private AddressRepository addressRepository;

    public String registerBuyer(BuyerDto buyerDto) {
        try {
            AddressDto addressDto = buyerDto.getAddressDto();

            Address address = Address.builder()
                    .addressLine1(addressDto.getAddressLine1())
                    .pincode(addressDto.getPincode())
                    .city(addressDto.getCity()).build();

            Buyer buyer = Buyer.builder().name(buyerDto.getName()).address(address).build();

            addressRepository.save(address);
            buyerRepository.save(buyer);
            return buyer.getId();
        }
        catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
