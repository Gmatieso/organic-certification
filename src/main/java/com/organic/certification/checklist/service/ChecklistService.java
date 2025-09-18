package com.organic.certification.checklist.service;

import com.organic.certification.checklist.dtos.CheckListRequest;
import com.organic.certification.checklist.entity.InspectionChecklist;
import com.organic.certification.checklist.repository.ChecklistRepository;
import com.organic.certification.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChecklistService {
    private final ChecklistRepository checklistRepository;

    public List<InspectionChecklist> getChecklistByInspection(UUID inspectionId) {
        return checklistRepository.findByInspectionId(inspectionId);
    }

    public void submitAnswers(List<CheckListRequest> answers) {
        for (CheckListRequest req : answers) {
            InspectionChecklist checklist = checklistRepository.findById(req.checklistId())
                    .orElseThrow(() -> new ResourceNotFoundException("Checklist not found: " + req.checklistId()));
            checklist.setAnswer(req.answer());
            checklistRepository.save(checklist);
        }
    }
}