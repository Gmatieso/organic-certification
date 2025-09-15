package com.organic.certification.farmer.service;

import com.organic.certification.common.exception.BadRequestException;
import com.organic.certification.farmer.dtos.FarmerRequest;
import com.organic.certification.farmer.dtos.FarmerResponse;
import com.organic.certification.farmer.entity.Farmer;
import com.organic.certification.farmer.mappers.FarmerMapper;
import com.organic.certification.farmer.repository.FarmerRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class FarmerServiceImpl implements FarmerService {

    private final FarmerRepository farmerRepository;
    private final FarmerMapper farmerMapper;

    @Override
    public FarmerResponse createFarmer(FarmerRequest request) {
        Farmer farmer = farmerMapper.toEntity(request);

        if (farmerRepository.existsByEmail(farmer.getEmail())) {
            throw new BadRequestException(" Sorry Email already exists");
        } else if (farmerRepository.existsByPhone(farmer.getPhone())) {
            throw new BadRequestException(" Sorry Phone already exists");
        }

        return farmerMapper.toResponse(farmerRepository.save(farmer));
    }

    @Override
    public FarmerResponse updateFarmer(UUID id, FarmerRequest request) {
        return null;
    }

    @Override
    public Void deleteFarmer(UUID id) {
        return null;
    }

    @Override
    public Page<FarmerResponse> getAllFarmers(Pageable pageable) {
        return null;
    }

    @Override
    public FarmerResponse getFarmer(UUID id) {
        return null;
    }
}
