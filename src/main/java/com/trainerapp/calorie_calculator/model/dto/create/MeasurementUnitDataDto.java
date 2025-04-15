package com.trainerapp.calorie_calculator.model.dto.create;

import com.trainerapp.calorie_calculator.enums.UnitType;

public record MeasurementUnitDataDto(

        Long foodId,
        UnitType unit,
        Double gramsPerUnit) {
}

