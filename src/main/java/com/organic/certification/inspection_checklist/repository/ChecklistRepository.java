package com.organic.certification.inspection_checklist.repository;

import com.organic.certification.inspection_checklist.entity.InspectionChecklist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChecklistRepository extends JpaRepository<InspectionChecklist, UUID> {
}
