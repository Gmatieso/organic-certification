package com.organic.certification.inspection.controller;

import com.organic.certification.common.config.ApiConfig;
import com.organic.certification.common.response.ApiResponseEntity;
import com.organic.certification.inspection.dtos.InspectionRequest;
import com.organic.certification.inspection.dtos.InspectionResponse;
import com.organic.certification.inspection.service.InspectionService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(InspectionController.PATH)
@AllArgsConstructor
public class InspectionController {
    public static final String PATH = ApiConfig.BASE_API_PATH + "inspection";
    private final InspectionService inspectionService;

    @PostMapping
    public ResponseEntity<?> createInspection(@Valid @RequestBody InspectionRequest inspectionRequest) {
        InspectionResponse response = inspectionService.createInspection(inspectionRequest);
        return ApiResponseEntity.success("Inspection created successfully", response);
    }




    @PostMapping("{id}/complete")
    public ResponseEntity<?> completeInspection(@PathVariable UUID id) {
        InspectionResponse response = inspectionService.completeInspection(id);
        return ApiResponseEntity.success("Inspection completed successfully", response);
    }



    @PutMapping("{id}")
    public ResponseEntity<?> updateInspection(@PathVariable UUID id, @Valid @RequestBody InspectionRequest inspectionRequest) {
        InspectionResponse response = inspectionService.updateInspection(id,inspectionRequest);
        return ApiResponseEntity.success("Inspection updated successfully", response);
    }

    @GetMapping
    public ResponseEntity<?> getInspections(Pageable pageable) {
        Page<InspectionResponse> responses = inspectionService.getInspections(pageable);
        return ApiResponseEntity.success("Inspections retrieved successfully", responses);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getInspection(@PathVariable UUID id) {
        InspectionResponse response = inspectionService.getInspection(id);
        return ApiResponseEntity.success("Inspection retrieved successfully", response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteInspection(@PathVariable UUID id) {
        inspectionService.deleteInspection(id);
        return ApiResponseEntity.success("Inspection deleted successfully", null);
    }
}

