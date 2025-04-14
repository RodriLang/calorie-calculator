package com.trainerapp.calorie_calculator.repository;

import com.trainerapp.calorie_calculator.model.entity.CustomIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomIngredientRepository extends JpaRepository<CustomIngredient, Long> {

    public CustomIngredient findEntityById(Long id);
}
