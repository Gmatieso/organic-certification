package com.organic.certification.checklist.dtos;

import java.util.UUID;

public record CheckListResponse(
        UUID id,
        String question,
        Boolean answer
) {
}
