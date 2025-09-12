package com.organic.certification.inspection_checklist.repository;

import com.organic.certification.inspection_checklist.entity.InspectionChecklist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InspectionChecklistRepository extends JpaRepository<InspectionChecklist, Integer> {
}
