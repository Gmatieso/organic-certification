package com.organic.certification.inspection_checklist.service;

import com.organic.certification.inspection_checklist.dtos.CheckListRequest;
import com.organic.certification.inspection_checklist.dtos.CheckListResponse;
import com.organic.certification.inspection_checklist.entity.InspectionChecklist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CheckListService {
    CheckListResponse createCheckList(CheckListRequest checkListRequest);
    CheckListResponse updateCheckList(UUID id, CheckListRequest checkListRequest);
    void deleteCheckList(UUID id);
    Page<CheckListResponse> getCheckLists(Pageable pageable);
    CheckListResponse getCheckListById(UUID id);
    InspectionChecklist getCheckListByIdOrThrow(UUID id);
}
