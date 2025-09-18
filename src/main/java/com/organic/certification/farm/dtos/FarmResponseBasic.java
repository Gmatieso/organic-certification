package com.organic.certification.farm.dtos;

import java.util.UUID;

public record FarmResponseBasic(
        UUID id,
        String farmName,
        String location,
        Double areaHa
) {
}
