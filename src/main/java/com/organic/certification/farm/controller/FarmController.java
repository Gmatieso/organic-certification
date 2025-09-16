package com.organic.certification.farm.controller;

import com.organic.certification.common.config.ApiConfig;
import com.organic.certification.common.response.ApiResponseEntity;
import com.organic.certification.farm.dtos.FarmRequest;
import com.organic.certification.farm.dtos.FarmResponse;
import com.organic.certification.farm.service.FarmService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(FarmController.PATH)
@AllArgsConstructor
public class FarmController {

    public static final String PATH = ApiConfig.BASE_API_PATH + "farm";
    private final FarmService farmService;

    @PostMapping
    public ResponseEntity<?> createFarm(@Valid  @RequestBody FarmRequest farmRequest) {
        FarmResponse response = farmService.createFarm(farmRequest);
        return ApiResponseEntity.success("Farm created successfully", response);
    }

    @GetMapping
    public ResponseEntity<?> getAllFarms(Pageable pageable) {
        Page<FarmResponse> response = farmService.getAllFarms(pageable);
        return ApiResponseEntity.success("Farms found", response);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getFarm(@PathVariable UUID id) {
       FarmResponse response = farmService.getFarm(id);
        return ApiResponseEntity.success("Farm retrieved successfully", response);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateFarm(@PathVariable UUID id, @RequestBody FarmRequest farmRequest){
        FarmResponse response = farmService.updateFarm(id, farmRequest);
        return ApiResponseEntity.success("Farm updated successfully", response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteFarm(@PathVariable UUID id){
        farmService.deleteFarm(id);
        return ApiResponseEntity.success("Farm deleted successfully", null);
    }
}
