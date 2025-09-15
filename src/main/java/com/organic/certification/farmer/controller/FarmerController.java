package com.organic.certification.farmer.controller;

import com.organic.certification.common.config.ApiConfig;
import com.organic.certification.common.response.ApiResponseEntity;
import com.organic.certification.farmer.dtos.FarmerRequest;
import com.organic.certification.farmer.dtos.FarmerResponse;
import com.organic.certification.farmer.service.FarmerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(FarmerController.PATH)
@AllArgsConstructor
public class FarmerController {

    public static final String PATH = ApiConfig.BASE_API_PATH + "farmer";

    private final FarmerService farmerService;

    @PostMapping
    public ResponseEntity<?> createFarmer(@Valid @RequestBody FarmerRequest  farmerRequest) {
        FarmerResponse response = farmerService.createFarmer(farmerRequest);
        return ApiResponseEntity.success("Farmer Created successfully", response);
    }

    @GetMapping
    public ResponseEntity<?> getAllFarmers(Pageable pageable) {
        Page<FarmerResponse> response = farmerService.getAllFarmers(pageable);
        return ApiResponseEntity.success("Farmers retrieved successfully", response);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getFarmer(@PathVariable UUID id){
        FarmerResponse response = farmerService.getFarmer(id);
        return ApiResponseEntity.success("Farmer retrieved successfully", response);
    }

    @PutMapping({"id"})
    public ResponseEntity<?> updateFarmer(@PathVariable UUID id, @RequestBody FarmerRequest  farmerRequest) {
        FarmerResponse response = farmerService.updateFarmer(id, farmerRequest);
        return ApiResponseEntity.success("Farmer updated successfully", response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteFarmer(@PathVariable UUID id){
             farmerService.deleteFarmer(id);
             return ApiResponseEntity.success("Farmer deleted successfully", null);
    }



}
