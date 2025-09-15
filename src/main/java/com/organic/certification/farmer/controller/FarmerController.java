package com.organic.certification.farmer.controller;

import com.organic.certification.common.config.ApiConfig;
import com.organic.certification.common.response.ApiResponseEntity;
import com.organic.certification.farmer.dtos.FarmerRequest;
import com.organic.certification.farmer.dtos.FarmerResponse;
import com.organic.certification.farmer.service.FarmerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
