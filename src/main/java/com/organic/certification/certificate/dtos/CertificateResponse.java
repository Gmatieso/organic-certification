package com.organic.certification.certificate.dtos;

import com.organic.certification.farm.dtos.FarmResponse;
import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record CertificateResponse(
        UUID id,
        String certificateNumber,
        LocalDate issueDate,
        LocalDate expiryDate,
        String pdfUrl,
        Double complianceScore,
        FarmResponse farmResponse) {
}
