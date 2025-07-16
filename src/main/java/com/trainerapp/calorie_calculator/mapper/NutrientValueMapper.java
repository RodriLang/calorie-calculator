package com.trainerapp.calorie_calculator.mapper;

import com.trainerapp.calorie_calculator.dto.response.NutrientValueResponseDto;
import com.trainerapp.calorie_calculator.enums.NutrientType;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface NutrientValueMapper {

    /* ---------- ENTITY → DTO ---------- */
    @Mapping(target = "value", source = "value") // (opcional, se sobre‑entiende)
    @Mapping(target = "unit", source = "nutrientType", qualifiedByName = "nutrientTypeToUnit")
    NutrientValueResponseDto map(Double value, NutrientType nutrientType);

    /* ---------- DTO → Double ---------- */
    // Tiene que ser default (o static) para que MapStruct pueda usarlo.
    @Named("dtoToValue")
    default Double map(NutrientValueResponseDto dto) {
        return dto != null ? dto.value() : null;
    }

    /// ---------- METODO AUXILIAR ---------- */
    // Convierte el enum en su abreviatura (o en la unidad que vos definas).
    @Named("nutrientTypeToUnit")
    static String nutrientTypeToUnit(NutrientType type) {
        return type != null ? type.getUnit() /* o getAbbreviation() */ : null;
    }
}
