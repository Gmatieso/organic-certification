package com.organic.certification.checklist.mappers;

import com.organic.certification.checklist.dtos.CheckListRequest;
import com.organic.certification.checklist.dtos.CheckListResponse;
import com.organic.certification.checklist.entity.InspectionChecklist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface CheckListMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "inspection", ignore = true)
    InspectionChecklist toEntity(CheckListRequest checkListRequest);

    @Mapping(target = "answer", source = "answer")
    CheckListResponse toResponse(InspectionChecklist checklist);

}
