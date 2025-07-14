package com.trainerapp.calorie_calculator.model.dto;

import lombok.Builder;

@Builder
public record TagDto(
        Long id,
        String label) {
}
