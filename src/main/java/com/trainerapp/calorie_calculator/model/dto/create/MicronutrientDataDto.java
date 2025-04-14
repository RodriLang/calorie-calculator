package com.trainerapp.calorie_calculator.model.dto.create;

import com.trainerapp.calorie_calculator.enums.MicronutrientType;
import com.trainerapp.calorie_calculator.enums.UnitType;

public record MicronutrientDataDto(
        String name,
        Double dailyAmount,
        UnitType unit,
        MicronutrientType type) {
}
