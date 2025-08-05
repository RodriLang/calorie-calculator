package com.trainerapp.calorie_calculator.web.dto.response;

import java.util.UUID;

public record MeasurementUnitResponseDto(

        UUID id,

        Long foodId,

        String unit,

        Double gramsPerUnit
) {
}
