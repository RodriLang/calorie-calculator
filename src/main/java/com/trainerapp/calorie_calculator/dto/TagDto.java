package com.trainerapp.calorie_calculator.dto;

import lombok.Builder;

@Builder
public record TagDto(
        Long id,
        String label) {
}
