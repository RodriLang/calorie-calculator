package com.trainerapp.calorie_calculator.domain.repository;

import com.trainerapp.calorie_calculator.domain.enums.UnitType;
import com.trainerapp.calorie_calculator.infrastructure.persistence.entity.MeasurementUnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MeasurementUnitRepository extends JpaRepository<MeasurementUnitEntity, Long> {

    Optional<MeasurementUnitEntity> findByPublicId(UUID id);

    Optional<MeasurementUnitEntity> findByUnitAndFood_PublicId(UnitType unit, UUID foodId);

    List<MeasurementUnitEntity> findByFood_PublicId(UUID foodId);
}

