package com.organic.certification.inspection.repository;

import com.organic.certification.inspection.entity.Inspection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InspectionRepository extends JpaRepository<Inspection, Integer> {
}
