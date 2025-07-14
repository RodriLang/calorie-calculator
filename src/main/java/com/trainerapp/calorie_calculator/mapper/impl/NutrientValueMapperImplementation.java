package com.trainerapp.calorie_calculator.mapper.impl;

import com.trainerapp.calorie_calculator.enums.NutrientType;
import com.trainerapp.calorie_calculator.mapper.NutrientValueMapper;
import com.trainerapp.calorie_calculator.dto.NutrientValueDto;
import org.springframework.stereotype.Component;

@Component
public class NutrientValueMapperImplementation implements NutrientValueMapper {

    public NutrientValueDto map(Double value, NutrientType nutrientType) {
        if (value == null || nutrientType == null) return null;

        return new NutrientValueDto(value, nutrientType.getUnit());  // Usa el unit del enum
    }

    private Double map(NutrientValueDto dto) {
        return dto != null ? dto.value() : null;
    }
}


