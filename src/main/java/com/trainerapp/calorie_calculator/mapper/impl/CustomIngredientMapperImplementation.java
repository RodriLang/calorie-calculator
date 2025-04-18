package com.trainerapp.calorie_calculator.mapper.impl;

import com.trainerapp.calorie_calculator.enums.UnitType;
import com.trainerapp.calorie_calculator.model.dto.CustomIngredientDto;
import com.trainerapp.calorie_calculator.model.dto.create.CustomIngredientDataDto;
import com.trainerapp.calorie_calculator.model.entity.Seasoning;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CustomIngredientMapperImplementation implements com.trainerapp.calorie_calculator.mapper.CustomIngredientMapper {

    public Seasoning fromDto(CustomIngredientDto customIngredientDto){
        UnitType unitType = Arrays
                .stream(UnitType.values())
                .filter(u->u.getAbbreviation().equals(customIngredientDto.unit()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        return Seasoning.builder()
                .food(customIngredientDto.food())
                .quantity(customIngredientDto.quantity())
                .unit(unitType)
                .note(customIngredientDto.note())
                .build();
    }

    public CustomIngredientDto toDto(Seasoning seasoning){
        return CustomIngredientDto.builder()
                .food(seasoning.getFood())
                .quantity(seasoning.getAmount())
                .unit(seasoning.getUnit().getAbbreviation())
                .note(seasoning.getNote())
                .build();
    }

    public Seasoning fromDataDto(CustomIngredientDataDto customIngredientDataDto){

        return Seasoning.builder()
                .food(customIngredientDataDto.food())
                .quantity(customIngredientDataDto.quantity())
                .unit(customIngredientDataDto.unit())
                .note(customIngredientDataDto.note())
                .build();
    }
}
