package com.organic.certification.field.service;

import com.organic.certification.field.dtos.FieldRequest;
import com.organic.certification.field.dtos.FieldResponse;
import com.organic.certification.field.entity.Field;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface FieldService {
    FieldResponse createField(FieldRequest fieldRequest);
    FieldResponse updateField(UUID id, FieldRequest fieldRequest);
    void deleteField(UUID id);
    Page<FieldResponse> getAllFields(Pageable pageable);
    FieldResponse getField(UUID id);
    Field getFieldByIdOrThrow(UUID id);
}
