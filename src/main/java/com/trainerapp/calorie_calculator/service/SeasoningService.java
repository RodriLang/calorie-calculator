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
                .name(seasoningDataDto.food())
                .unit(seasoningDataDto.unit())
                .amount(seasoningDataDto.quantity())
                .label(seasoningDataDto.note())
                .build();
    }

    public void update(Seasoning ingredient, SeasoningDataDto data) {

        ingredient.setName(data.food());
        ingredient.setAmount(data.quantity());
        ingredient.setUnit(data.unit());
    }
}

