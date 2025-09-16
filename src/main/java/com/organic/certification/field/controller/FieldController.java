package com.organic.certification.field.controller;

import com.organic.certification.common.config.ApiConfig;
import com.organic.certification.common.response.ApiResponseEntity;
import com.organic.certification.field.dtos.FieldRequest;
import com.organic.certification.field.dtos.FieldResponse;
import com.organic.certification.field.service.FieldService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(FieldController.PATH)
@AllArgsConstructor
public class FieldController {
    public static final String PATH = ApiConfig.BASE_API_PATH +  "fields";
    private FieldService fieldService;

    @PostMapping
    public ResponseEntity<?> createField(@Valid  @RequestBody FieldRequest fieldRequest) {
        FieldResponse fieldResponse = fieldService.createField(fieldRequest);
        return ApiResponseEntity.success("Field created successfully", fieldResponse);
    }
}
