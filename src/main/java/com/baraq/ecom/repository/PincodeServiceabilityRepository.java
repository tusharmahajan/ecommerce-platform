package com.baraq.ecom.repository;

import com.baraq.ecom.models.PincodeServiceability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PincodeServiceabilityRepository extends JpaRepository<PincodeServiceability, String> {

    @Query(value = "select * from pincode_serviceability ps where ps.source_pincode = :sourcePincode " +
            "and ps.destination_pincode = :destinationPincode", nativeQuery = true)
    PincodeServiceability getServiceabilityDetails(String sourcePincode, String destinationPincode);
}
