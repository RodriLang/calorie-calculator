package com.trainerapp.calorie_calculator.application.service.impl;

import com.trainerapp.calorie_calculator.application.exception.IngredientNotFoundException;
import com.trainerapp.calorie_calculator.application.service.MeasurementUnitService;
import com.trainerapp.calorie_calculator.domain.model.Food;
import com.trainerapp.calorie_calculator.domain.model.Ingredient;
import com.trainerapp.calorie_calculator.domain.model.MeasurementUnit;
import com.trainerapp.calorie_calculator.infrastructure.persistence.entity.MeasurementUnitEntity;
import com.trainerapp.calorie_calculator.infrastructure.persistence.mapper.IngredientEntityMapper;
import com.trainerapp.calorie_calculator.web.dto.request.IngredientRequestDto;
import com.trainerapp.calorie_calculator.domain.repository.IngredientRepository;
import com.trainerapp.calorie_calculator.application.service.IngredientService; // Importar la interfaz
import com.trainerapp.calorie_calculator.web.mapper.IngredientDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class IngredientServiceImpl implements IngredientService { // Implementar la interfaz

    private final IngredientRepository ingredientRepository;
    private final FoodServiceImpl foodService;
    private final MeasurementUnitService measurementUnitService;
    private final IngredientDtoMapper ingredientDtoMapper;
    private final IngredientEntityMapper ingredientEntityMapper;

    @Override
    public Ingredient create(IngredientRequestDto ingredientRequestDto) {

        return Ingredient.builder()
                .food( foodService.findModelById(ingredientRequestDto.foodId()))
                .unit(measurementUnitService.findModelById(ingredientRequestDto.measurementUnitId()))
                .amount(ingredientRequestDto.amount())
                .build();
    }

    @Override
    public Ingredient getModelById(Long id) {
        return ingredientEntityMapper.toModel(ingredientRepository.findById(id)
                .orElseThrow(() -> new IngredientNotFoundException(id)));
    }

    @Override
    public void update(Ingredient ingredient, IngredientRequestDto ingredientRequestDto) {
        MeasurementUnit unit = measurementUnitService.findModelById(ingredientRequestDto.measurementUnitId());
        Food food = foodService.findModelById(ingredientRequestDto.foodId());
        ingredient.toBuilder()
                .food(food)
                .unit(unit)
                .amount(ingredientRequestDto.amount())
                .build();
    }
}
