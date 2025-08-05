package com.trainerapp.calorie_calculator.domain.repository;

import com.trainerapp.calorie_calculator.domain.enums.UnitType;
import com.trainerapp.calorie_calculator.infrastructure.persistence.entity.MeasurementUnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MeasurementUnitRepository extends JpaRepository<MeasurementUnitEntity, Long> {

    public Optional<MeasurementUnitEntity> findByUnitAndFood_Id(UnitType unit, Long foodId);

    public List<MeasurementUnitEntity> findByFood_Id(Long foodId);
}

