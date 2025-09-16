package com.organic.certification.field.repository;

import com.organic.certification.field.entity.Field;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FieldRepository extends JpaRepository<Field, UUID> {
}
