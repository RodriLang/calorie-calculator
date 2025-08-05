package com.trainerapp.calorie_calculator.web.dto.response;

import java.util.UUID;

public record MicronutrientResponseDto(

        UUID id,

        String name,

        Double dailyAmount,

        String unit,

        String type
) {
}
