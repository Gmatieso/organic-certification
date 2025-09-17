package com.organic.certification.inspection_checklist.dtos;
import com.organic.certification.inspection.dtos.InspectionResponse;

import java.util.UUID;

public record CheckListResponse(
        UUID id,
        String question,
        Boolean answer,
        InspectionResponse inspectionResponse
) {
}
