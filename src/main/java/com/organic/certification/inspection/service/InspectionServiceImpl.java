package com.organic.certification.inspection.service;

import com.organic.certification.inspection.dtos.InspectionRequest;
import com.organic.certification.inspection.dtos.InspectionResponse;
import com.organic.certification.inspection.entity.Inspection;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class InspectionServiceImpl implements InspectionService {
    @Override
    public InspectionResponse createInspection(InspectionRequest inspectionRequest) {
        return null;
    }

    @Override
    public InspectionResponse updateInspection(UUID id, InspectionRequest inspectionRequest) {
        return null;
    }

    @Override
    public void deleteInspection(UUID id) {

    }

    @Override
    public Page<InspectionResponse> getInspections(PageRequest pageRequest) {
        return null;
    }

    @Override
    public InspectionResponse getInspection(UUID id) {
        return null;
    }

    @Override
    public Inspection getInspectionByIdOrThrow(UUID id) {
        return null;
    }
}
