package com.organic.certification.inspection.dtos;

import com.organic.certification.common.enums.InspectionEnum;
import com.organic.certification.farm.dtos.FarmResponse;

import java.time.LocalDate;
import java.util.UUID;

public record InspectionResponse(
        UUID id,
        LocalDate date,
        InspectionEnum status,
        Double complianceScore,
        FarmResponse farmResponse
        ) {
}
