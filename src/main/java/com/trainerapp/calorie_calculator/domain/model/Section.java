package com.trainerapp.calorie_calculator.domain.model;

import com.trainerapp.calorie_calculator.domain.enums.DifficultyType;
import lombok.*;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class Section {

    private UUID publicId;

    private String name;

    private List<Ingredient> ingredients;

    private List<Seasoning> seasonings;

    private String description;

    private List<Step> steps;

    private Duration preparationTime;

    private DifficultyType difficulty;
}