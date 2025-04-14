package com.trainerapp.calorie_calculator.mapper;

import com.trainerapp.calorie_calculator.model.dto.IngredientDto;
import com.trainerapp.calorie_calculator.model.dto.NutrientValueDto;
import com.trainerapp.calorie_calculator.model.dto.create.IngredientDataDto;
import com.trainerapp.calorie_calculator.model.entity.Ingredient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {NutrientValueMapper.class,
MeasurementUnitMapper.class,
})
public interface IngredientMapper {

    @Mapping(target = "food.nutritionalInfo.carbohydrates",
            source = "food.nutritionalInfo.carbohydrates",
            qualifiedByName = "doubleToNutrientValueDto",
            defaultValue = "carbohydrates")  // Hardcodeamos el tipo de nutriente
    @Mapping(target = "food.nutritionalInfo.protein",
            source = "food.nutritionalInfo.protein",
            qualifiedByName = "doubleToNutrientValueDto",
            defaultValue = "protein")  // Hardcodeamos el tipo de nutriente
    Ingredient fromDto(IngredientDto ingredientDto);
    IngredientDto toDto(Ingredient ingredient);

    Ingredient fromDataDto(IngredientDataDto ingredientDataDto);

    default Double mapNutrientValues(NutrientValueDto value){
        return value != null ? value.value() : null;
    }
}

