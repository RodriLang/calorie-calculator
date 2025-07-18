package com.trainerapp.calorie_calculator.dto.response;

public record MeasurementUnitResponseDto(
        Long id,
        Long foodId,
        String unit,
        Double gramsPerUnit) {
}
