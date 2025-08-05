package com.trainerapp.calorie_calculator.domain.model;

import com.trainerapp.calorie_calculator.domain.enums.UnitType;
import lombok.*;

@Getter
@Builder(toBuilder = true)
@EqualsAndHashCode
@AllArgsConstructor
public class MeasurementUnit {

    private Food food;

    private UnitType unit;

    private Double gramsPerUnit;
}
