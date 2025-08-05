package com.trainerapp.calorie_calculator.domain.repository;

import com.trainerapp.calorie_calculator.infrastructure.persistence.entity.MicronutrientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MicronutrientRepository extends JpaRepository<MicronutrientEntity, Long> {

    public Optional<MicronutrientEntity> findByName(String name);
}
