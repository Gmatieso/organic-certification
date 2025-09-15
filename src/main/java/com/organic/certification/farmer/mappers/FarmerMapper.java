package com.organic.certification.farmer.mappers;

import com.organic.certification.farmer.dtos.FarmerRequest;
import com.organic.certification.farmer.dtos.FarmerResponse;
import com.organic.certification.farmer.entity.Farmer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FarmerMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "farms", ignore = true)
   Farmer toEntity(FarmerRequest request);

   @Mapping(target = "id", source ="id")
  FarmerResponse toResponse(Farmer farmer);
}
