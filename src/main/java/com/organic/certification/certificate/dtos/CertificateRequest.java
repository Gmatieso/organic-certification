package com.organic.certification.certificate.dtos;

import com.organic.certification.farm.entity.Farm;
import com.organic.certification.inspection.entity.Inspection;

import java.time.LocalDate;

public record CertificateRequest(
        String certificateNo,
        LocalDate issueDate,
        LocalDate expiryDate,
        String pdfUrl,
        Farm farmId
        /*TODO: Include Inspection inspectionId field*/
        ) {
}
