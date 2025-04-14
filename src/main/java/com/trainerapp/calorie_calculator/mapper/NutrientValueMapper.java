package com.trainerapp.calorie_calculator.mapper;

import com.trainerapp.calorie_calculator.enums.UnitType;
import com.trainerapp.calorie_calculator.model.dto.NutrientValueDto;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring",
uses = {MeasurementUnitMapper.class,
NutritionalInfoMapper.class,
IngredientMapper.class,
FoodMapper.class})
public interface NutrientValueMapper {

    @Named("doubleToNutrientValueDto")
    default NutrientValueDto map(Double value, String nutrientType) {
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

    @Named("nutrientValueDtoToDouble")
    default Double map(NutrientValueDto dto) {
        return dto != null ? dto.value() : null;
    }
}

