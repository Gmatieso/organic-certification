package com.organic.certification.inspection_checklist.mappers;

import com.organic.certification.inspection_checklist.dtos.CheckListRequest;
import com.organic.certification.inspection_checklist.dtos.CheckListResponse;
import com.organic.certification.inspection_checklist.entity.InspectionChecklist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface CheckListMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "inspection", ignore = true)
    InspectionChecklist toEntity(CheckListRequest checkListRequest);

    CheckListResponse toResponse(InspectionChecklist checklist);


}
