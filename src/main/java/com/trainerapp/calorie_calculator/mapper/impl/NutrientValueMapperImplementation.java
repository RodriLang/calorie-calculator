package com.trainerapp.calorie_calculator.mapper.impl;

import com.trainerapp.calorie_calculator.enums.UnitType;
import com.trainerapp.calorie_calculator.mapper.NutrientValueMapper;
import com.trainerapp.calorie_calculator.model.dto.NutrientValueDto;
import org.springframework.stereotype.Component;

@Component
public class NutrientValueMapperImplementation implements NutrientValueMapper {

    private NutrientValueDto map(Double value, String nutrientType) {
        if (value == null) return null;

        // Hardcodeamos el UnitType según el tipo de nutriente
        UnitType unitType;
        switch (nutrientType) {
            case "energy":
                unitType = UnitType.KILOCALORIES;  // Para la energía, se asigna kcal
                break;
            default:
                unitType = UnitType.GRAMS;  // Para otros nutrientes, se asigna gramos
                break;
        }

        return new NutrientValueDto(value, unitType.getAbbreviation());  // Crea el DTO con el valor y la unidad
    }

    private Double map(NutrientValueDto dto) {
        return dto != null ? dto.value() : null;
    }
}

