package com.organic.certification.farmer.dtos;

import java.util.UUID;

public record FarmerResponse(
        UUID id,
        String name,
        String phone,
        String email,
        String county
        ) {
}
