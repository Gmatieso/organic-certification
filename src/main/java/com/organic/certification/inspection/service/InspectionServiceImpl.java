package com.organic.certification.inspection.service;

import com.organic.certification.common.exception.ResourceNotFoundException;
import com.organic.certification.farm.entity.Farm;
import com.organic.certification.farm.service.FarmService;
import com.organic.certification.inspection.dtos.InspectionRequest;
import com.organic.certification.inspection.dtos.InspectionResponse;
import com.organic.certification.inspection.entity.Inspection;
import com.organic.certification.inspection.mappers.InspectionMapper;
import com.organic.certification.inspection.repository.InspectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class InspectionServiceImpl implements InspectionService {
    private final InspectionRepository inspectionRepository;
    private final InspectionMapper inspectionMapper;
    private final FarmService farmService;

    @Override
    public InspectionResponse createInspection(InspectionRequest inspectionRequest) {
        Inspection inspection = inspectionMapper.toEntity(inspectionRequest);
        Farm farm  = farmService.getFarmByIdOrThrow(inspectionRequest.farmId());
        inspection.setFarm(farm);
        return inspectionMapper.toResponse(inspectionRepository.save(inspection));
    }

    @Override
    public InspectionResponse updateInspection(UUID id, InspectionRequest inspectionRequest) {
        Inspection inspection = getInspectionByIdOrThrow(id);
        inspection.setDate(inspectionRequest.date());
        inspection.setInspectorName(inspectionRequest.inspectorName());
        inspection.setStatus(inspectionRequest.status());
        inspection.setComplianceScore(inspectionRequest.complianceScore());
        return inspectionMapper.toResponse(inspectionRepository.save(inspection));
    }

    @Override
    public void deleteInspection(UUID id) {

    }

    @Override
    public Page<InspectionResponse> getInspections(Pageable pageable) {
        Page<Inspection> inspectionPage =  inspectionRepository.findAll(pageable);
        return inspectionPage.map(inspectionMapper::toResponse);
    }

    @Override
    public InspectionResponse getInspection(UUID id) {
        return null;
    }

    @Override
    public Inspection getInspectionByIdOrThrow(UUID id) {
        return inspectionRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Inspection with id" + " " + id + " " + "not found"));
    }
}
