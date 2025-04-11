package com.trainerapp.calorie_calculator.model.dto;

import com.trainerapp.calorie_calculator.enums.MicronutrientType;
import com.trainerapp.calorie_calculator.enums.UnitType;

public record CreateMicronutrientDto(
        String name,
        double dailyAmount,
        UnitType unit,
        MicronutrientType type
) {}