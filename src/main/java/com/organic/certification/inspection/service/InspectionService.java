package com.organic.certification.inspection.service;

import com.organic.certification.inspection.dtos.InspectionRequest;
import com.organic.certification.inspection.dtos.InspectionResponse;
import com.organic.certification.inspection.entity.Inspection;
import com.organic.certification.inspection_checklist.dtos.CheckListRequest;
import com.organic.certification.inspection_checklist.dtos.CheckListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface InspectionService {
    InspectionResponse  createInspection(InspectionRequest inspectionRequest);
    InspectionResponse  updateInspection(UUID id, InspectionRequest inspectionRequest);
    void deleteInspection(UUID id);
    Page<InspectionResponse> getInspections(Pageable pageable);
    InspectionResponse  getInspection(UUID id);
    Inspection getInspectionByIdOrThrow(UUID id);
   InspectionResponse completeInspection(UUID id);

   // checklist-related method
    CheckListResponse addCheckListItem(UUID inspectionId, CheckListRequest request);
    CheckListResponse updateCheckListItem(UUID inspectionId, UUID checklistId,  CheckListRequest request);
    void deleteCheckListItem(UUID inspectionId, UUID checklistId);
    List<CheckListResponse> getCheckListsItems(UUID inspectionId);
}
