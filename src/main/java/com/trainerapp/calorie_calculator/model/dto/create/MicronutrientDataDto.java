package com.trainerapp.calorie_calculator.model.dto.create;

import com.trainerapp.calorie_calculator.enums.MicronutrientType;

public record CreateMicronutrientDto(
        String name,
        Double dailyAmount,
        String unit,
        MicronutrientType type) {
}
