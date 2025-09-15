package com.organic.certification.farm.repository;

import com.organic.certification.farm.entity.Farm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FarmRepository extends JpaRepository<Farm, UUID> {
    boolean existsByFarmName(String farmName);
}
