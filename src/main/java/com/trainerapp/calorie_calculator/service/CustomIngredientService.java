package com.trainerapp.calorie_calculator.service;

import com.trainerapp.calorie_calculator.model.dto.create.CustomIngredientDataDto;
import com.trainerapp.calorie_calculator.model.entity.CustomIngredient;
import com.trainerapp.calorie_calculator.repository.CustomIngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class CustomIngredientService {
    private final CustomIngredientRepository customIngredientRepository;
    private final MeasurementUnitService measurementUnitService;


    public CustomIngredient create(CustomIngredientDataDto customIngredientDataDto) {

        return CustomIngredient.builder()
                .food(customIngredientDataDto.food())
                .unit(customIngredientDataDto.unit())
                .quantity(customIngredientDataDto.quantity())
                .note(customIngredientDataDto.note())
                .build();
    }

    public void update(CustomIngredient ingredient, CustomIngredientDataDto data) {

        ingredient.setFood(data.food());
        ingredient.setQuantity(data.quantity());
        ingredient.setUnit(data.unit());
    }
}

