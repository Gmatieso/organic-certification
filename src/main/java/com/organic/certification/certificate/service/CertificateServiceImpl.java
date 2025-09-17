package com.organic.certification.certificate.service;

import com.organic.certification.certificate.dtos.CertificateRequest;
import com.organic.certification.certificate.dtos.CertificateResponse;
import com.organic.certification.certificate.entity.Certificate;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CertificateServiceImpl implements CertificateService {
    @Override
    public CertificateResponse createCertificate(CertificateRequest request) {
        return null;
    }

    @Override
    public CertificateResponse updateCertificate(UUID id, CertificateRequest request) {
        return null;
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
