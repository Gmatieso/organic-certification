package com.organic.certification.certificate.dtos;

import com.organic.certification.farm.entity.Farm;

import java.time.LocalDate;

public record CertificateRequest(
        String certificateNo,
        LocalDate issueDate,
        LocalDate expiryDate,
        String pdfUrl,
        Farm farmId
        ) {
}
