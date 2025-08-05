package com.trainerapp.calorie_calculator.web.dto.response;

import java.util.UUID;

public record SeasoningResponseDto(

        UUID id,

        String name,

        Double amount,

        String unit,

        String label
) {
}
