package com.organic.certification.inspection_checklist.dtos;

import java.util.UUID;

public record CheckListResponse(
        UUID id,
        String question,
        Boolean answer
) {
}
