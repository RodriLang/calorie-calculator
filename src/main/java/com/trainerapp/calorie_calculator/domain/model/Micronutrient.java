package com.trainerapp.calorie_calculator.domain.model;

import com.trainerapp.calorie_calculator.domain.enums.MicronutrientType;
import com.trainerapp.calorie_calculator.domain.enums.UnitType;
import lombok.*;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class Micronutrient {

    private String name;

    private Double dailyAmount;

    private UnitType unit;

    private MicronutrientType type;

}
