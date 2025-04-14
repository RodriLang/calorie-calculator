package com.trainerapp.calorie_calculator.model.dto;

import com.trainerapp.calorie_calculator.enums.UnitType;

public record MeasurementUnitDto(
        Long id,
        Long foodId,
        UnitType unit,
        Double gramsPerUnit) {
}
