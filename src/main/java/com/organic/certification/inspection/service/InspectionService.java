package com.organic.certification.inspection.service;

import com.organic.certification.inspection.dtos.InspectionRequest;
import com.organic.certification.inspection.dtos.InspectionResponse;
import com.organic.certification.inspection.entity.Inspection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface InspectionService {
    InspectionResponse  createInspection(InspectionRequest inspectionRequest);
    InspectionResponse  updateInspection(UUID id, InspectionRequest inspectionRequest);
    void deleteInspection(UUID id);
    Page<InspectionResponse> getInspections(Pageable pageable);
    InspectionResponse  getInspection(UUID id);
    Inspection getInspectionByIdOrThrow(UUID id);
   InspectionResponse completeInspection(UUID id);
}
