package com.organic.certification.farm.service;

import com.organic.certification.farm.dtos.FarmRequest;
import com.organic.certification.farm.dtos.FarmResponse;
import com.organic.certification.farm.entity.Farm;
import com.organic.certification.farmer.dtos.FarmerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public class FarmServiceImpl implements  FarmService {
    @Override
    public FarmResponse createFarm(FarmRequest request) {
        return null;
    }

    @Override
    public FarmResponse updateFarm(UUID id, FarmRequest request) {
        return null;
    }

    @Override
    public void deleteFarm(UUID id) {

    }

    @Override
    public Page<FarmResponse> getAllFarms(Pageable pageable) {
        return null;
    }

    @Override
    public FarmerResponse getFarm(UUID id) {
        return null;
    }

    @Override
    public Farm getFarmByIdOrThrow(UUID id) {
        return null;
    }
}
