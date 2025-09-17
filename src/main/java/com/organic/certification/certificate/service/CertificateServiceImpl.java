package com.organic.certification.certificate.service;

import com.organic.certification.certificate.dtos.CertificateRequest;
import com.organic.certification.certificate.dtos.CertificateResponse;
import com.organic.certification.certificate.entity.Certificate;
import com.organic.certification.certificate.mappers.CertificateMapper;
import com.organic.certification.certificate.repository.CertificateRepository;
import com.organic.certification.farm.entity.Farm;
import com.organic.certification.farm.service.FarmService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CertificateServiceImpl implements CertificateService {
    private final CertificateMapper certificateMapper;
    private final FarmService farmService;
    private final CertificateRepository certificateRepository;

    @Override
    public CertificateResponse createCertificate(CertificateRequest request) {
        Certificate certificate = certificateMapper.toEntity(request);
        Farm farm = farmService.getFarmByIdOrThrow(request.farmId().getId());
        certificate.setFarm(farm);
        return certificateMapper.toResponse(certificateRepository.save(certificate));
    }

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

    }

    @Override
    public Page<CertificateResponse> getCertificates(Pageable pageable) {
        return null;
    }

    @Override
    public CertificateResponse getCertificate(UUID id) {
        return null;
    }

    @Override
    public Certificate getCertificateByIdOrThrow(UUID id) {
        return null;
    }
}
