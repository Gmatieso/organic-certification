package com.organic.certification.certificate.entity;

import com.organic.certification.farm.entity.Farm;
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

    private String certificateNo;

    private LocalDate issueDate;

    private LocalDate expiryDate;

    private String pdfUrl;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farm_id", nullable = false)
    private Farm farm;
}
