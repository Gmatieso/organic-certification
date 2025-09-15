package com.organic.certification.farmer.dtos;

public record FarmerRequest(
        String name,
        String phone,
        String email,
        String county) {
}
