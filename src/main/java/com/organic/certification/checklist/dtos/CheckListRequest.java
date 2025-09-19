package com.organic.certification.checklist.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;


public record CheckListRequest(
        UUID checklistId,
        Boolean answer
) {
}
