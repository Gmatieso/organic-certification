package com.organic.certification.farmer.repository;

import com.organic.certification.farmer.entity.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


public interface FarmerRepository extends JpaRepository<Farmer, Integer> {
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);

    List<Farmer> id(UUID id);
}
