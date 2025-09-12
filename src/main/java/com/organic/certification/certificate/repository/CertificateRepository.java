package com.organic.certification.certificate.repository;

import com.organic.certification.certificate.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<Certificate, Integer> {
}
