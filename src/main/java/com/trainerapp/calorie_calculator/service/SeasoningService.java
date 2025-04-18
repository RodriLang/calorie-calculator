package com.trainerapp.calorie_calculator.service;

import com.trainerapp.calorie_calculator.model.dto.create.SeasoningDataDto;
import com.trainerapp.calorie_calculator.model.entity.Seasoning;
import com.trainerapp.calorie_calculator.repository.CustomIngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class SeasoningService {
    private final CustomIngredientRepository customIngredientRepository;
    private final MeasurementUnitService measurementUnitService;


    public Seasoning create(SeasoningDataDto seasoningDataDto) {

        return Seasoning.builder()
                .food(seasoningDataDto.food())
                .unit(seasoningDataDto.unit())
                .quantity(seasoningDataDto.quantity())
                .note(seasoningDataDto.note())
                .build();
    }

    public void update(Seasoning ingredient, SeasoningDataDto data) {

        ingredient.setFood(data.food());
        ingredient.setQuantity(data.quantity());
        ingredient.setUnit(data.unit());
    }
}

