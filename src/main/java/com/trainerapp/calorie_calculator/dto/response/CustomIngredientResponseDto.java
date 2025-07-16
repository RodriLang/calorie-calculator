package com.trainerapp.calorie_calculator.dto.response;

import lombok.Builder;

@Builder
public record CustomIngredientResponseDto(

        String food,
        Double quantity,
        String unit,
        String note) {
}
