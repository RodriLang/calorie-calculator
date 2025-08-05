package com.trainerapp.calorie_calculator.domain.model;

import lombok.*;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class Step {

    private Integer stepNumber;
    private String label;
    private String instructions;
}
