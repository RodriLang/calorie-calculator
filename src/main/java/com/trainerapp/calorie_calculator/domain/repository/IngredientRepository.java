package com.trainerapp.calorie_calculator.domain.repository;

import com.trainerapp.calorie_calculator.infrastructure.persistence.entity.IngredientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IngredientRepository extends JpaRepository<IngredientEntity, Long> {

    Optional<IngredientEntity> findByPublicId(UUID publicId);

}
