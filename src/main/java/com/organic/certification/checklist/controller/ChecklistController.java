package com.organic.certification.checklist.controller;

import com.organic.certification.checklist.dtos.CheckListRequest;
import com.organic.certification.checklist.dtos.CheckListResponse;
import com.organic.certification.checklist.entity.InspectionChecklist;
import com.organic.certification.checklist.service.ChecklistService;
import com.organic.certification.common.config.ApiConfig;
import com.organic.certification.common.response.ApiResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ApiConfig.BASE_API_PATH + "checklists")
@RequiredArgsConstructor
public class ChecklistController {

    private final ChecklistService checklistService;

    @GetMapping("/inspection/{inspectionId}")
    public ResponseEntity<?> getChecklist(@PathVariable UUID inspectionId) {
        List<CheckListResponse> checklist = checklistService.getChecklistByInspection(inspectionId);
        return ApiResponseEntity.success("Checklist retrieved successfully", checklist);
    }

    @PostMapping("/answers")
    public ResponseEntity<?> submitAnswers(@RequestBody List<CheckListRequest> answers) {
        checklistService.submitAnswers(answers);
        return ApiResponseEntity.success("Checklist answers submitted successfully", null);
    }
}
