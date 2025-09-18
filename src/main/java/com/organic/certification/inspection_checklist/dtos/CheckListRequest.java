package com.organic.certification.inspection_checklist.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record CheckListRequest(
        @NotBlank(message = "Question is required")
        String question,
        @NotNull(message = "Answer must be true or false")
        Boolean answer
) {
}
