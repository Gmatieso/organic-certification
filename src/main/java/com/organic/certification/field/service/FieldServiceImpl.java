package com.organic.certification.field.service;

import com.organic.certification.farm.entity.Farm;
import com.organic.certification.farm.service.FarmService;
import com.organic.certification.field.dtos.FieldRequest;
import com.organic.certification.field.dtos.FieldResponse;
import com.organic.certification.field.entity.Field;
import com.organic.certification.field.mappers.FieldMapper;
import com.organic.certification.field.repository.FieldRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class FieldServiceImpl implements FieldService {

    private final FieldRepository fieldRepository;
    private final FieldMapper fieldMapper;
    private final FarmService farmService;

    @Override
    public FieldResponse createField(FieldRequest fieldRequest) {
        Field field = fieldMapper.toEntity(fieldRequest);
        Farm farm = farmService.getFarmByIdOrThrow(fieldRequest.farmId());
        field.setFarm(farm);
        return fieldMapper.toResponse(fieldRepository.save(field));
    }

    @Override
    public FieldResponse updateField(UUID id, FieldRequest fieldRequest) {
        Field field = getFieldByIdOrThrow(id);
        field.setName(fieldRequest.name());
        field.setCrop(fieldRequest.crop());
        field.setAreaHa(fieldRequest.areaHa());
        return fieldMapper.toResponse(fieldRepository.save(field));
    }

    @Override
    public void deleteField(UUID id) {

    }

    @Override
    public Page<FieldResponse> getAllFields(PageRequest pageRequest) {
        Page<Field> fieldPage = fieldRepository.findAll(pageRequest);
        return fieldPage.map(fieldMapper::toResponse);
    }

    @Override
    public FieldResponse getField(UUID id) {
        return null;
    }

    @Override
    public Field getFieldByIdOrThrow(UUID id) {
        return null;
    }
}
