package com.organic.certification.inspection.dtos;

import com.organic.certification.common.enums.InspectionEnum;
import com.organic.certification.inspection.entity.Inspection;

import java.time.LocalDate;
import java.util.UUID;

public record InspectionRequest(
        LocalDate date,
        String inspectorName,
        InspectionEnum status,
        Double complianceScore,
        UUID farmId
        ) {
}
