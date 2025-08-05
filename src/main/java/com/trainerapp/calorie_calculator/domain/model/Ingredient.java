package com.trainerapp.calorie_calculator.domain.model;

import lombok.*;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class Ingredient {

    private Food food;

    private Double amount; //2 unidades, 100 ml, 3 cucharadas.

    private String displayName;

    private MeasurementUnit unit;

}
