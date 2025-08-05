package com.trainerapp.calorie_calculator.domain.model;

import lombok.*;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class MicronutrientContent {

    private Micronutrient micronutrient;

    private Double amountPerUnit; // Cantidad del micronutriente por unidad (gramo o microgramo)
}