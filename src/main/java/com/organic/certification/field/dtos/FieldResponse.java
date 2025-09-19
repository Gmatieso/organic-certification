package com.organic.certification.field.dtos;

import com.organic.certification.farm.dtos.FarmResponse;
import com.organic.certification.farm.dtos.FarmResponseBasic;

import java.util.UUID;

public record FieldResponse(
        UUID id,
        String name,
        String crop,
        Double areaHa,
        FarmResponseBasic farmResponse
        ) {
}
