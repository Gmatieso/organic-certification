package com.organic.certification.certificate.repository;

import com.organic.certification.certificate.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CertificateRepository extends JpaRepository<Certificate, UUID> {
}
