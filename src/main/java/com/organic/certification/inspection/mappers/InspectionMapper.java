package com.organic.certification.inspection.mappers;

import com.organic.certification.inspection.dtos.InspectionRequest;
import com.organic.certification.inspection.dtos.InspectionResponse;
import com.organic.certification.inspection.entity.Inspection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InspectionMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "farm", ignore = true)
    Inspection toEntity(InspectionRequest inspectionRequest);

    @Mapping(source = "farm", target = "farmResponse")
    InspectionResponse toResponse(Inspection inspection);

}
