package com.organic.certification.farm.mappers;

import com.organic.certification.farm.dtos.FarmRequest;
import com.organic.certification.farm.dtos.FarmResponse;
import com.organic.certification.farm.entity.Farm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FarmMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "farmer", ignore = true)
    @Mapping(target = "fields", ignore = true)
    @Mapping(target = "inspections", ignore = true)
    @Mapping(target = "certificate", ignore = true)
    Farm toEntity(FarmRequest farmRequest);

    @Mapping(target = "farmerResponse",  ignore = true)
    FarmResponse toResponse(Farm farm);
}
