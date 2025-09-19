package com.organic.certification.certificate.entity;

import com.organic.certification.farm.entity.Farm;
import com.organic.certification.inspection.entity.Inspection;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "certificates")
@Data
public class Certificate {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "certificate_number", unique = true, nullable = false)
    private String certificateNumber;

    private LocalDate issueDate;

    private LocalDate expiryDate;

    private String pdfUrl;

    private Double complianceScore;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inspection_id", nullable = false)
    private Inspection inspection;
}

