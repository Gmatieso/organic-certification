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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String question;

    private Boolean answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inspection_id", nullable = false)
    private Inspection inspection;
}
