package com.trainerapp.calorie_calculator.domain.model;

import com.trainerapp.calorie_calculator.domain.enums.FoodOriginType;
import com.trainerapp.calorie_calculator.domain.enums.NutritionalFunctionType;
import lombok.*;

import java.util.List;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class Food {

    private String name;

    private FoodOriginType foodOrigin;

    private List<NutritionalFunctionType> nutritionalFunctions;

    private List<MeasurementUnit> measurementUnits;

    private NutritionalInfo nutritionalInfo;

    private List<MicronutrientContent> micronutrients;

    private List<Tag> tags;
}