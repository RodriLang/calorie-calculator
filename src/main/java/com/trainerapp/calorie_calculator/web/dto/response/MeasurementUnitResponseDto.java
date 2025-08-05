package com.trainerapp.calorie_calculator.web.dto.response;

public record MeasurementUnitResponseDto(
        Long id,
        Long foodId,
        String unit,
        Double gramsPerUnit) {
}
