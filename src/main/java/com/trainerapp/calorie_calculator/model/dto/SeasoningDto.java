package com.trainerapp.calorie_calculator.model.dto;

import lombok.Builder;

@Builder
public record SeasoningDto(

        String food,
        Double quantity,
        String unit,
        String note) {
}
