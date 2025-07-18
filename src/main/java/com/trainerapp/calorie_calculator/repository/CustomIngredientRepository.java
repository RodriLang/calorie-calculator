package com.trainerapp.calorie_calculator.repository;

import com.trainerapp.calorie_calculator.model.entity.Seasoning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomIngredientRepository extends JpaRepository<Seasoning, Long> {

    public Seasoning findEntityById(Long id);
}
