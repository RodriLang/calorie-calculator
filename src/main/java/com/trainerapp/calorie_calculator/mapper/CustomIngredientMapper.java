package com.trainerapp.calorie_calculator.mapper;

import com.trainerapp.calorie_calculator.dto.response.SeasoningResponseDto;
import com.trainerapp.calorie_calculator.dto.request.SeasoningRequestDto;
import com.trainerapp.calorie_calculator.enums.UnitType;
import com.trainerapp.calorie_calculator.model.entity.Seasoning;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CustomIngredientMapper {

    @Mapping(target = "unit", source = "unit", qualifiedByName = "fromAbbreviation")
    Seasoning fromDto(SeasoningResponseDto dto);

    @Mapping(target = "unit", source = "unit", qualifiedByName = "toAbbreviation")
    SeasoningResponseDto toDto(Seasoning entity);

    // No necesita conversión, los campos coinciden en tipo
    Seasoning toEntity(SeasoningRequestDto dto);

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

