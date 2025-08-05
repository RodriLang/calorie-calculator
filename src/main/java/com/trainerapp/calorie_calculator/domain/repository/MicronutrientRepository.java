package com.trainerapp.calorie_calculator.domain.repository;

import com.trainerapp.calorie_calculator.infrastructure.persistence.entity.MicronutrientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MicronutrientRepository extends JpaRepository<MicronutrientEntity, Long> {

    Optional<MicronutrientEntity> findByName(String name);

    Optional<MicronutrientEntity> findByPublicId(UUID publicId);

    boolean existsByPublicId(UUID publicId);
}
