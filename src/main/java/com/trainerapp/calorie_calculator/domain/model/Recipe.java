package com.trainerapp.calorie_calculator.domain.model;

import lombok.*;

import java.util.List;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class Recipe {

    private String name;

    private String description;

    private String imageUrl;

    private String preparationTime;

    private Integer servings;

    private List<Section> sections;

    private List<Tag> tagList;

}