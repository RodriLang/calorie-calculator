package com.trainerapp.calorie_calculator.repository;

import com.trainerapp.calorie_calculator.enums.UnitType;
import com.trainerapp.calorie_calculator.model.entity.MeasurementUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MeasurementUnitRepository extends JpaRepository<MeasurementUnit, Long> {

    public Optional<MeasurementUnit> findByUnitAndFood_Id(UnitType unit, Long foodId);

    public List<MeasurementUnit> findByFood_Id(Long foodId);
}

