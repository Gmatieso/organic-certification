package com.organic.certification.inspection_checklist.entity;

import com.organic.certification.inspection.entity.Inspection;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "inspection_checklists")
@Data
public class InspectionChecklist {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private String question;

    private Boolean answer = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inspection_id", nullable = false)
    private Inspection inspection;
}
