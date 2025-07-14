package com.trainerapp.calorie_calculator.dto;

import com.trainerapp.calorie_calculator.enums.UnitType;
import lombok.Builder;

@Builder
public record MeasurementUnitDto(
        Long id,
        Long foodId,
        UnitType unit,
        Double gramsPerUnit) {
}
