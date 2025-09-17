package com.organic.certification.certificate.dtos;

import java.time.LocalDate;
import java.util.UUID;

public record CertificateRequest(
        String certificateNo,
        LocalDate issueDate,
        LocalDate expiryDate,
        String pdfUrl,
        UUID farmId
        ) {
}
