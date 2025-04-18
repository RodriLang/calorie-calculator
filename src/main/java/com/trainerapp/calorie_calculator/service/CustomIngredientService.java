package com.trainerapp.calorie_calculator.service;

import com.trainerapp.calorie_calculator.model.dto.create.CustomIngredientDataDto;
import com.trainerapp.calorie_calculator.model.entity.Seasoning;
import com.trainerapp.calorie_calculator.repository.CustomIngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class CustomIngredientService {
    private final CustomIngredientRepository customIngredientRepository;
    private final MeasurementUnitService measurementUnitService;


    public Seasoning create(CustomIngredientDataDto customIngredientDataDto) {

        return Seasoning.builder()
                .food(customIngredientDataDto.food())
                .unit(customIngredientDataDto.unit())
                .quantity(customIngredientDataDto.quantity())
                .note(customIngredientDataDto.note())
                .build();
    }

    public void update(Seasoning ingredient, CustomIngredientDataDto data) {

        ingredient.setFood(data.food());
        ingredient.setAmount(data.quantity());
        ingredient.setUnit(data.unit());
    }
}

