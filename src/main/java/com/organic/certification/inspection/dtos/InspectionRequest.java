package com.organic.certification.inspection.dtos;

import com.organic.certification.common.enums.InspectionEnum;

import java.time.LocalDate;
import java.util.UUID;

public record InspectionRequest(
        LocalDate date,
        String inspectorName,
        UUID farmId
        ) {
}
