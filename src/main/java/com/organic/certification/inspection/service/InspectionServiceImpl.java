package com.organic.certification.inspection.service;

import com.organic.certification.certificate.service.CertificateService;
import com.organic.certification.common.enums.InspectionEnum;
import com.organic.certification.common.exception.ResourceNotFoundException;
import com.organic.certification.farm.entity.Farm;
import com.organic.certification.farm.service.FarmService;
import com.organic.certification.inspection.dtos.InspectionRequest;
import com.organic.certification.inspection.dtos.InspectionResponse;
import com.organic.certification.inspection.entity.Inspection;
import com.organic.certification.inspection.mappers.InspectionMapper;
import com.organic.certification.inspection.repository.InspectionRepository;
import com.organic.certification.checklist.dtos.CheckListRequest;
import com.organic.certification.checklist.dtos.CheckListResponse;
import com.organic.certification.checklist.entity.InspectionChecklist;
import com.organic.certification.checklist.mappers.CheckListMapper;
import com.organic.certification.checklist.repository.ChecklistRepository;
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
    private final CertificateService certificateService;
    private final CheckListMapper checkListMapper;

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
            certificateService.generateCertificate(inspection);
        }else {
            inspection.setStatus(InspectionEnum.REJECTED);
        }
        inspectionRepository.save(inspection);
        return inspectionMapper.toResponse(inspection);
    }

    @Override
    public CheckListResponse addCheckListItem(UUID inspectionId, CheckListRequest request) {
        Inspection inspection = getInspectionByIdOrThrow(inspectionId);
        InspectionChecklist checklist = checkListMapper.toEntity(request);
        checklist.setInspection(inspection);
        checklistRepository.save(checklist);
        return checkListMapper.toResponse(checklist);
    }

    @Override
    public CheckListResponse updateCheckListItem(UUID inspectionId, UUID checklistId, CheckListRequest request) {
        Inspection inspection = getInspectionByIdOrThrow(inspectionId);
        InspectionChecklist checklist = checklistRepository.findByIdAndInspectionId(checklistId, inspection.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Checklist not found for inspection"));

        checklist.setQuestion(request.question());
        checklist.setAnswer(request.answer());
        return checkListMapper.toResponse(checklistRepository.save(checklist));
    }

    @Override
    public List<CheckListResponse> getCheckListsItems(UUID inspectionId) {

        return checklistRepository.findByInspectionId(inspectionId)
                .stream()
                .map(checkListMapper::toResponse)
                .toList();
    }


}
