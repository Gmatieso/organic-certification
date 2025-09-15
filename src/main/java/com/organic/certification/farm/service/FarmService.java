package com.organic.certification.farm.service;

import com.organic.certification.farm.dtos.FarmRequest;
import com.organic.certification.farm.dtos.FarmResponse;
import com.organic.certification.farm.entity.Farm;
import com.organic.certification.farmer.dtos.FarmerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface FarmService {
    FarmResponse createFarm(FarmRequest request);
    FarmResponse updateFarm(UUID id, FarmRequest request);
    void deleteFarm(UUID id);
    Page<FarmResponse> getAllFarms(Pageable pageable);
    FarmerResponse getFarm(UUID id);
    Farm getFarmByIdOrThrow(UUID id);
}
