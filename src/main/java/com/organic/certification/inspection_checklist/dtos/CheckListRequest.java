package com.organic.certification.inspection_checklist.dtos;

import java.util.UUID;

public record CheckListRequest(
        String question,
        Boolean answer,
        UUID inspection_id
) {
}
