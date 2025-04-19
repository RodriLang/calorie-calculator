package com.trainerapp.calorie_calculator.repository;

import com.trainerapp.calorie_calculator.model.entity.RecipeSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeSectionRepository extends JpaRepository<RecipeSection, Long> {
}
