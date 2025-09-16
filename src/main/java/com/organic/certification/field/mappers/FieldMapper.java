package com.organic.certification.field.mappers;

import com.organic.certification.field.dtos.FieldRequest;
import com.organic.certification.field.dtos.FieldResponse;
import com.organic.certification.field.entity.Field;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FieldMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "farm", ignore = true)
    Field toEntity(FieldRequest fieldRequest);

    @Mapping(target = "farmResponse", ignore = true)
    FieldResponse toResponse(Field field);
}
