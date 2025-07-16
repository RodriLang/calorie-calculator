package com.trainerapp.calorie_calculator.dto.response;

import com.trainerapp.calorie_calculator.enums.UnitType;
import lombok.Builder;

@Builder
public record MeasurementUnitResponseDto(
        Long id,
        Long foodId,
        UnitType unit,
        Double gramsPerUnit) {
}
