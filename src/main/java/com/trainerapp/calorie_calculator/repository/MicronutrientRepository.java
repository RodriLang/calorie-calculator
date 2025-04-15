package com.trainerapp.calorie_calculator.repository;

import com.trainerapp.calorie_calculator.model.entity.Micronutrient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MicronutrientRepository extends JpaRepository<Micronutrient, Long> {

    public Optional<Micronutrient> findByName(String name);
}
