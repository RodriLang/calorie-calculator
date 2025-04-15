package com.trainerapp.calorie_calculator.model.dto.create;

import lombok.Builder;

@Builder
public record MicronutrientContentDataDto(
        Long micronutrientId,
        Double amountPerUnit) {
}