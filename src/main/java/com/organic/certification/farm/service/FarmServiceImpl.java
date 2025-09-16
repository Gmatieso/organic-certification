package com.organic.certification.farm.service;

import com.organic.certification.common.exception.ResourceNotFoundException;
import com.organic.certification.farm.dtos.FarmRequest;
import com.organic.certification.farm.dtos.FarmResponse;
import com.organic.certification.farm.entity.Farm;
import com.organic.certification.farm.mappers.FarmMapper;
import com.organic.certification.farm.repository.FarmRepository;
import com.organic.certification.farmer.entity.Farmer;
import com.organic.certification.farmer.service.FarmerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class FarmServiceImpl implements  FarmService {

    private final FarmRepository farmRepository;
    private final FarmMapper farmMapper;
    private final FarmerService farmerService;

    @Override
    public FarmResponse createFarm(FarmRequest request) {
        Farm farm = farmMapper.toEntity(request);
        Farmer farmer = farmerService.getFarmerByIdOrThrow(request.farmerId());
        farm.setFarmer(farmer);
        return farmMapper.toResponse(farmRepository.save(farm));
    }

    @Override
    public FarmResponse updateFarm(UUID id, FarmRequest request) {
        Farm farm = getFarmByIdOrThrow(id);
        farm.setFarmName(request.farmName());
        farm.setLocation(request.location());
        farm.setAreaHa(request.areaHa());
        return farmMapper.toResponse(farmRepository.save(farm));
    }

    @Override
    public void deleteFarm(UUID id) {
        Farm farm = getFarmByIdOrThrow(id);
        farmRepository.delete(farm);
    }

    @Override
    public Page<FarmResponse> getAllFarms(Pageable pageable) {
        Page<Farm> farmPage = farmRepository.findAll(pageable);
        return farmPage.map(farmMapper::toResponse);
    }

    @Override
    public FarmResponse getFarm(UUID id) {
        Farm farm = getFarmByIdOrThrow(id);
        return farmMapper.toResponse(farm);
    }

    @Override
    public Farm getFarmByIdOrThrow(UUID id) {
        return farmRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Farm with id" + " " + id + " " + "not found"));
    }
}
