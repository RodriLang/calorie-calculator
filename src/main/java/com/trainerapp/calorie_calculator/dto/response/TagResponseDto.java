package com.trainerapp.calorie_calculator.dto.response;

import lombok.Builder;

@Builder
public record TagResponseDto(
        Long id,
        String label) {
}
