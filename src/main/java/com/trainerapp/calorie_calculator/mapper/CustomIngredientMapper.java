package com.trainerapp.calorie_calculator.mapper;

import com.trainerapp.calorie_calculator.dto.response.CustomIngredientResponseDto;
import com.trainerapp.calorie_calculator.dto.request.CustomIngredientRequestDto;
import com.trainerapp.calorie_calculator.enums.UnitType;
import com.trainerapp.calorie_calculator.model.entity.CustomIngredient;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CustomIngredientMapper {

    @Mapping(target = "unit", source = "unit", qualifiedByName = "fromAbbreviation")
    CustomIngredient fromDto(CustomIngredientResponseDto dto);

    @Mapping(target = "unit", source = "unit", qualifiedByName = "toAbbreviation")
    CustomIngredientResponseDto toDto(CustomIngredient entity);

    // No necesita conversión, los campos coinciden en tipo
    CustomIngredient fromDataDto(CustomIngredientRequestDto dto);

    // Métodos auxiliares para conversión de enum
    @Named("fromAbbreviation")
    static UnitType mapUnit(String abbreviation) {
        return UnitType.fromAbbreviation(abbreviation);
    }

    @Named("toAbbreviation")
    static String mapUnit(UnitType unitType) {
        return unitType != null ? unitType.getAbbreviation() : null;
    }
}

