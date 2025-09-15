package com.organic.certification.inspection.entity;

import com.organic.certification.common.enums.InspectionEnum;
import com.organic.certification.farm.entity.Farm;
import com.organic.certification.inspection_checklist.entity.InspectionChecklist;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "inspections")
@Data
public class Inspection {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private LocalDate date;
    private String inspectorName;

    @Enumerated(EnumType.STRING)
    private InspectionEnum status;

    private Double complianceScore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farm_id", nullable = false)
    private Farm farm;


    @OneToMany(mappedBy = "inspection", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InspectionChecklist> checklist = new ArrayList<>();
}
