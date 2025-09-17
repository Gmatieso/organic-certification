package com.organic.certification.inspection_checklist.controller;

import com.organic.certification.common.config.ApiConfig;
import com.organic.certification.common.response.ApiResponseEntity;
import com.organic.certification.inspection_checklist.dtos.CheckListRequest;
import com.organic.certification.inspection_checklist.dtos.CheckListResponse;
import com.organic.certification.inspection_checklist.service.CheckListService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(CheckListController.PATH)
@AllArgsConstructor
public class CheckListController {
    public static final String PATH = ApiConfig.BASE_API_PATH + "checklists";
    private final CheckListService checkListService;

    @PostMapping
    public ResponseEntity<?> createChecklist(@RequestBody CheckListRequest checkList) {
        CheckListResponse response = checkListService.createCheckList(checkList);
        return ApiResponseEntity.success("Checklist created successfully", response);
    }
    @PutMapping("{id}")
    public ResponseEntity<?> updateChecklist(@PathVariable UUID id, @RequestBody CheckListRequest checkList) {
        CheckListResponse response = checkListService.updateCheckList(id, checkList);
        return ApiResponseEntity.success("Checklist updated successfully", response);
    }

    @GetMapping
    public ResponseEntity<?> getCheckLists(Pageable pageable) {
        Page<CheckListResponse> response = checkListService.getCheckLists(pageable);
        return ApiResponseEntity.success("Checklists retrieved successfully", response);
    }

}
