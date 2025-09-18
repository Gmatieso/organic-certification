package com.organic.certification.inspection.service;

import com.organic.certification.common.enums.InspectionEnum;
import com.organic.certification.common.exception.ResourceNotFoundException;
import com.organic.certification.farm.entity.Farm;
import com.organic.certification.farm.service.FarmService;
import com.organic.certification.inspection.dtos.InspectionRequest;
import com.organic.certification.inspection.dtos.InspectionResponse;
import com.organic.certification.inspection.entity.Inspection;
import com.organic.certification.inspection.mappers.InspectionMapper;
import com.organic.certification.inspection.repository.InspectionRepository;
import com.organic.certification.inspection_checklist.entity.InspectionChecklist;
import com.organic.certification.inspection_checklist.repository.ChecklistRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class InspectionServiceImpl implements InspectionService {
    private final InspectionRepository inspectionRepository;
    private final InspectionMapper inspectionMapper;
    private final FarmService farmService;
    private final ChecklistRepository checklistRepository;

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
        Inspection inspection = getInspectionByIdOrThrow(id);
        inspectionRepository.delete(inspection);
    }

    @Override
    public Page<InspectionResponse> getInspections(Pageable pageable) {
        Page<Inspection> inspectionPage =  inspectionRepository.findAll(pageable);
        return inspectionPage.map(inspectionMapper::toResponse);
    }

    @Override
    public InspectionResponse getInspection(UUID id) {
        Inspection inspection = getInspectionByIdOrThrow(id);
        return inspectionMapper.toResponse(inspection);
    }

    @Override
    public Inspection getInspectionByIdOrThrow(UUID id) {
        return inspectionRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Inspection with id" + " " + id + " " + "not found"));
    }

    @Override
    public InspectionResponse completeInspection(UUID id) {
        Inspection inspection = getInspectionByIdOrThrow(id);

        // fetch checklist items for this inspection
       List<InspectionChecklist> checklists = checklistRepository.findByInspectionId(id);

       if(checklists.isEmpty()){
           throw new ResourceNotFoundException("Inspection has no checklist items");
       }

       long yesCount = checklists.stream()
               .filter(InspectionChecklist::getAnswer) // true = Yes
               .count();

        double score = ((double) yesCount / checklists.size()) * 100;
        inspection.setComplianceScore(score);

        // update status based on score
        if (score >= 80.0) {
            inspection.setStatus(InspectionEnum.APPROVED);
        }else {
            inspection.setStatus(InspectionEnum.REJECTED);
        }
        inspectionRepository.save(inspection);
        return inspectionMapper.toResponse(inspection);
    }


}
