package com.organic.certification.certificate.service;

import com.organic.certification.certificate.dtos.CertificateResponse;
import com.organic.certification.certificate.entity.Certificate;
import com.organic.certification.inspection.entity.Inspection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CertificateService {
    Page<CertificateResponse> getCertificates(Pageable pageable);
    CertificateResponse getCertificate(UUID id);
    Certificate getCertificateByIdOrThrow(UUID id);
    CertificateResponse generateCertificate(Inspection inspection);
    byte[] generateCertificatePdf(Certificate certificate);
    void updatePdfUrl(UUID id, String pdfUrl);
}
