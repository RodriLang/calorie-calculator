package com.trainerapp.calorie_calculator.model.dto.create;

import com.trainerapp.calorie_calculator.enums.UnitType;

public record CreateMeasurementUnitDto(
        UnitType unit,
        Double gramsPerUnit) {
}

