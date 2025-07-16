package com.trainerapp.calorie_calculator.service;

import com.trainerapp.calorie_calculator.dto.request.CustomIngredientRequestDto;
import com.trainerapp.calorie_calculator.model.entity.CustomIngredient;
import com.trainerapp.calorie_calculator.repository.CustomIngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class CustomIngredientService {
    private final CustomIngredientRepository customIngredientRepository;
    private final MeasurementUnitService measurementUnitService;


    public CustomIngredient create(CustomIngredientRequestDto customIngredientRequestDto) {

        return CustomIngredient.builder()
                .food(customIngredientRequestDto.food())
                .unit(customIngredientRequestDto.unit())
                .quantity(customIngredientRequestDto.quantity())
                .note(customIngredientRequestDto.note())
                .build();
    }

    public void update(CustomIngredient ingredient, CustomIngredientRequestDto data) {

        ingredient.setFood(data.food());
        ingredient.setQuantity(data.quantity());
        ingredient.setUnit(data.unit());
    }
}

