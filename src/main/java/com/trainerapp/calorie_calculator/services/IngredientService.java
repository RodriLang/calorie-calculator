package com.trainerapp.calorie_calculator.services;

import com.trainerapp.calorie_calculator.models.Food;
import com.trainerapp.calorie_calculator.models.Ingredient;
import com.trainerapp.calorie_calculator.repositories.FoodRepository;
import com.trainerapp.calorie_calculator.repositories.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public List<Ingredient> getAll(){
        return ingredientRepository.findAll();
    }
}
