package com.trainerapp.calorie_calculator.domain.model;

import com.trainerapp.calorie_calculator.domain.enums.UnitType;
import lombok.*;

import java.util.UUID;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class Seasoning {

    private UUID publicId;

    private String name;

    private Double amount;

    private UnitType unit;

    private String label;
}
