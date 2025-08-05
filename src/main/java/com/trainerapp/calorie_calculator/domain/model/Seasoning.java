package com.trainerapp.calorie_calculator.domain.model;

import com.trainerapp.calorie_calculator.domain.enums.UnitType;
import lombok.*;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class Seasoning {

    private String name;

    private Double amount;

    private UnitType unit;

    private String label;
}
