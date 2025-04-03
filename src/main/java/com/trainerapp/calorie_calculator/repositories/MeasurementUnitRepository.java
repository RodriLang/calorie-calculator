package com.trainerapp.calorie_calculator.repositories;

import com.trainerapp.calorie_calculator.models.Food;
import com.trainerapp.calorie_calculator.models.MeasurementUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementUnitRepository extends JpaRepository<MeasurementUnit, Long> {
}
