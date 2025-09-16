package com.organic.certification.certificate.dtos;

import com.organic.certification.farm.dtos.FarmResponse;

import java.time.LocalDate;
import java.util.UUID;

public record CertificateResponse(
        UUID id,
        String CertificateNo,
        LocalDate issueDate,
        LocalDate expiryDate,
        String pdfUrl,
        FarmResponse farmResponse) {
}
