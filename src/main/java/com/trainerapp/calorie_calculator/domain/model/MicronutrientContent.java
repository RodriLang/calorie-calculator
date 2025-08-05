package com.trainerapp.calorie_calculator.domain.model;

import lombok.*;

import java.util.UUID;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class MicronutrientContent {

    private UUID publicId;

    private Micronutrient micronutrient;

    private Double amountPerUnit; // Cantidad del micronutriente por unidad (gramo o microgramo)
}