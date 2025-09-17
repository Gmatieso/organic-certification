package com.organic.certification.inspection_checklist.service;

import com.organic.certification.common.exception.ResourceNotFoundException;
import com.organic.certification.inspection.entity.Inspection;
import com.organic.certification.inspection.service.InspectionService;
import com.organic.certification.inspection_checklist.dtos.CheckListRequest;
import com.organic.certification.inspection_checklist.dtos.CheckListResponse;
import com.organic.certification.inspection_checklist.entity.InspectionChecklist;
import com.organic.certification.inspection_checklist.mappers.CheckListMapper;
import com.organic.certification.inspection_checklist.repository.ChecklistRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CheckListServiceImpl implements CheckListService {
    private final CheckListMapper checkListMapper;
    private final InspectionService inspectionService;
    private final ChecklistRepository checklistRepository;

    @Override
    public CheckListResponse createCheckList(CheckListRequest checkListRequest) {
        InspectionChecklist checklist = checkListMapper.toEntity(checkListRequest);
        Inspection inspection = inspectionService.getInspectionByIdOrThrow(checkListRequest.inspection_id());
        checklist.setInspection(inspection);
        return checkListMapper.toResponse(checklistRepository.save(checklist));
    }

    @Override
    public CheckListResponse updateCheckList(UUID id, CheckListRequest checkListRequest) {
        InspectionChecklist checklist = getCheckListByIdOrThrow(id);
        checklist.setQuestion(checkListRequest.question());
        checklist.setAnswer(checkListRequest.answer());
        return checkListMapper.toResponse(checklistRepository.save(checklist));
    }

    @Override
    public void deleteCheckList(UUID id) {

    }

    @Override
    public Page<CheckListResponse> getCheckLists(Pageable pageable) {
        return null;
    }

    @Override
    public CheckListResponse getCheckListById(UUID id) {
        return null;
    }

    @Override
    public InspectionChecklist getCheckListByIdOrThrow(UUID id) {
        return checklistRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("CheckList with id" + " " + id + " " + "not found"));
    }
}
