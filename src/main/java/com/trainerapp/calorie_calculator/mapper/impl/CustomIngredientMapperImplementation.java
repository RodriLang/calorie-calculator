package com.trainerapp.calorie_calculator.mapper.impl;

import com.trainerapp.calorie_calculator.enums.UnitType;
import com.trainerapp.calorie_calculator.model.dto.CustomIngredientDto;
import com.trainerapp.calorie_calculator.model.dto.create.CustomIngredientDataDto;
import com.trainerapp.calorie_calculator.model.entity.CustomIngredient;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CustomIngredientMapperImplementation implements com.trainerapp.calorie_calculator.mapper.CustomIngredientMapper {

    public CustomIngredient fromDto(CustomIngredientDto customIngredientDto){
        UnitType unitType = Arrays
                .stream(UnitType.values())
                .filter(u->u.getAbbreviation().equals(customIngredientDto.unit()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        return CustomIngredient.builder()
                .food(customIngredientDto.food())
                .quantity(customIngredientDto.quantity())
                .unit(unitType)
                .note(customIngredientDto.note())
                .build();
    }

    public CustomIngredientDto toDto(CustomIngredient customIngredient){
        return CustomIngredientDto.builder()
                .food(customIngredient.getFood())
                .quantity(customIngredient.getQuantity())
                .unit(customIngredient.getUnit().getAbbreviation())
                .note(customIngredient.getNote())
                .build();
    }

    public CustomIngredient fromDataDto(CustomIngredientDataDto customIngredientDataDto){

        return CustomIngredient.builder()
                .food(customIngredientDataDto.food())
                .quantity(customIngredientDataDto.quantity())
                .unit(customIngredientDataDto.unit())
                .note(customIngredientDataDto.note())
                .build();
    }
}
