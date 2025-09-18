package com.organic.certification.certificate.service;

import com.organic.certification.certificate.dtos.CertificateRequest;
import com.organic.certification.certificate.dtos.CertificateResponse;
import com.organic.certification.certificate.entity.Certificate;
import com.organic.certification.certificate.mappers.CertificateMapper;
import com.organic.certification.certificate.repository.CertificateRepository;
import com.organic.certification.common.exception.ResourceNotFoundException;
import com.organic.certification.inspection.entity.Inspection;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CertificateServiceImpl implements CertificateService {
    private final CertificateMapper certificateMapper;
    private final CertificateRepository certificateRepository;


    @Override
    public CertificateResponse updateCertificate(UUID id, CertificateRequest request) {
        Certificate certificate = getCertificateByIdOrThrow(id);
        certificate.setCertificateNo(request.certificateNo());
        certificate.setExpiryDate(request.expiryDate());
        certificate.setPdfUrl(request.pdfUrl());
        certificate.setIssueDate(request.issueDate());
        return certificateMapper.toResponse(certificateRepository.save(certificate));
    }

    @Override
    public void deleteCertificate(UUID id) {
        Certificate certificate = getCertificateByIdOrThrow(id);
        certificateRepository.delete(certificate);
    }

    @Override
    public Page<CertificateResponse> getCertificates(Pageable pageable) {
        Page<Certificate> certificatesPage = certificateRepository.findAll(pageable);
        return certificatesPage.map(certificateMapper::toResponse);
    }

    @Override
    public CertificateResponse getCertificate(UUID id) {
        Certificate certificate = getCertificateByIdOrThrow(id);
        return certificateMapper.toResponse(certificate);
    }

    @Override
    public Certificate getCertificateByIdOrThrow(UUID id) {
        return certificateRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Certificate with id" + " " + id + " " + " not found "));
    }

    @Override
    public CertificateResponse generateCertificate(Inspection inspection) {
        Certificate certificate = new Certificate();
        certificate.setCertificateNo("CERT-" + UUID.randomUUID());
        certificate.setIssueDate(LocalDate.now());
        certificate.setExpiryDate(LocalDate.now().plusYears(1));
        certificate.setPdfUrl(null); // handled later
        certificate.setFarm(inspection.getFarm());
        return certificateMapper.toResponse(certificateRepository.save(certificate));
    }
}
