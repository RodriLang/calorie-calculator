package com.trainerapp.calorie_calculator.web.mapper;

import com.trainerapp.calorie_calculator.domain.enums.NutrientType;
import com.trainerapp.calorie_calculator.web.dto.response.NutrientValueResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface NutrientValueDtoMapper {


    @Mapping(target = "unit", source = "nutrientType", qualifiedByName = "nutrientTypeToUnit")
    NutrientValueResponseDto map(Double value, NutrientType nutrientType);

    @Named("dtoToValue")
    default Double map(NutrientValueResponseDto dto) {
        return dto != null ? dto.value() : null;
    }

    /// ---------- METODO AUXILIAR ----------
    // Convierte el enum en su abreviatura
    @Named("nutrientTypeToUnit")
    static String nutrientTypeToUnit(NutrientType type) {
        return type != null ? type.getUnit() : null;
    }
}
