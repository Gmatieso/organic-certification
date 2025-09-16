package com.organic.certification.field.dtos;

import java.util.UUID;

public record FieldRequest(
        String name,
        String crop,
        Double areaHa,
        UUID farmId
        ) {
}
