package com.trainerapp.calorie_calculator.service.impl;

import com.trainerapp.calorie_calculator.dto.request.SeasoningRequestDto;
import com.trainerapp.calorie_calculator.model.entity.Seasoning;
import com.trainerapp.calorie_calculator.repository.CustomIngredientRepository;
import com.trainerapp.calorie_calculator.service.SeasoningService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SeasoningServiceImpl implements SeasoningService {

    private final CustomIngredientRepository customIngredientRepository;
    private final MeasurementUnitServiceImpl measurementUnitServiceImpl;

    @Override
    public Seasoning create(SeasoningRequestDto seasoningRequestDto) {
        return Seasoning.builder()
                .name(seasoningRequestDto.name())
                .unit(seasoningRequestDto.unit())
                .amount(seasoningRequestDto.amount())
                .label(seasoningRequestDto.label())
                .build();
    }

    @Override
    public void update(Seasoning ingredient, SeasoningRequestDto data) {
        ingredient.setLabel(data.label());
        ingredient.setName(data.name());
        ingredient.setAmount(data.amount());
        ingredient.setUnit(data.unit());
    }
}
