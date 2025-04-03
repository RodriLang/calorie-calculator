package com.trainerapp.calorie_calculator.services;

import com.trainerapp.calorie_calculator.models.Micronutrient;
import com.trainerapp.calorie_calculator.repositories.MicronutrientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MicronutrientService {

    private final MicronutrientRepository micronutrientRepository;

    public MicronutrientService(MicronutrientRepository ingredientRepository) {
        this.micronutrientRepository = ingredientRepository;
    }

    public List<Micronutrient> getMicronutrients() {
        return micronutrientRepository.findAll();
    }
}
