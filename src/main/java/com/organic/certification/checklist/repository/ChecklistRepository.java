package com.organic.certification.checklist.repository;

import com.organic.certification.checklist.entity.InspectionChecklist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChecklistRepository extends JpaRepository<InspectionChecklist, UUID> {
    List<InspectionChecklist> findByInspectionId(UUID inspectionId);
    Optional<InspectionChecklist> findByIdAndInspectionId(UUID checklistId, UUID inspectionId);

}
