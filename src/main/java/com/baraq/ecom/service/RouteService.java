package com.baraq.ecom.service;

import com.baraq.ecom.dto.PincodeServiceabilityDto;
import com.baraq.ecom.models.Address;
import com.baraq.ecom.models.PincodeServiceability;
import com.baraq.ecom.repository.PincodeServiceabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteService {

    @Autowired
    private PincodeServiceabilityRepository pincodeServiceabilityRepository;

    public String storePincodeServiceability(PincodeServiceabilityDto pincodeMappingDto) {

        try {
            PincodeServiceability pincodeServiceability = new PincodeServiceability();

            Address sourceAddress = Address.builder().pincode(pincodeMappingDto.getSourcePincode()).build();
            pincodeServiceability.setSourcePincode(sourceAddress);

            Address destinationAddress = Address.builder().pincode(pincodeMappingDto.getDestinationPincode()).build();
            pincodeServiceability.setDestinationPincode(destinationAddress);

            pincodeServiceability.setPaymentMode(pincodeMappingDto.getPaymentMode());

            pincodeServiceabilityRepository.save(pincodeServiceability);
            return "Stored pincode serviceability details";
        }
        catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
