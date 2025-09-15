package com.organic.certification.farm.dtos;

import java.util.UUID;

public record FarmRequest(
        String farmName,
        String location,
        Double areaHa,
        UUID farmerId
        ) {
}
