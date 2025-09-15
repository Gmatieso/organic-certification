package com.organic.certification.farm.dtos;

import com.organic.certification.farmer.dtos.FarmerResponse;

import java.util.UUID;

public record FarmResponse(
        UUID id,
        String farmName,
        String location,
        Double areaHa,
        FarmerResponse farmerResponse
        ) {
}
