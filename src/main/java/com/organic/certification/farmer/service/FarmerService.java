package com.organic.certification.farmer.service;

import com.organic.certification.farmer.dtos.FarmerRequest;
import com.organic.certification.farmer.dtos.FarmerResponse;
import com.organic.certification.farmer.entity.Farmer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface FarmerService {
    FarmerResponse createFarmer(FarmerRequest request);
    FarmerResponse updateFarmer(UUID id, FarmerRequest request);
    void deleteFarmer(UUID id);
    Page<FarmerResponse> getAllFarmers(Pageable pageable);
    FarmerResponse getFarmer(UUID id);
    Farmer getFarmerByIdOrThrow(UUID id);

}
