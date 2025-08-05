package com.trainerapp.calorie_calculator.domain.model;

import com.trainerapp.calorie_calculator.domain.enums.DifficultyType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Duration;
import java.util.List;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class Section {

    private String name;

    private List<Ingredient> ingredients;

    private List<Seasoning> seasonings;

    private String description;

    private List<Step> steps;

    private Duration preparationTime;

    private DifficultyType difficulty;
}