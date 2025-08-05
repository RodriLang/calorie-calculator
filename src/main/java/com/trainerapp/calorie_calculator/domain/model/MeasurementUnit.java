package com.trainerapp.calorie_calculator.domain.model;

import com.trainerapp.calorie_calculator.domain.enums.UnitType;
import lombok.*;

import java.util.UUID;

@Getter
@Builder(toBuilder = true)
@EqualsAndHashCode
@AllArgsConstructor
public class MeasurementUnit {

    private UUID publicId;

    private Food food;

    private UnitType unit;

    private Double gramsPerUnit;
}
