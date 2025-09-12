package com.organic.certification.farmer.repository;

import com.organic.certification.farmer.entity.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmerRepository extends JpaRepository<Farmer, Integer> {
}
