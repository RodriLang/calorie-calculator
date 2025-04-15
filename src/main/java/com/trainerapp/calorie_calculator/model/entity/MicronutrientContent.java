package com.trainerapp.calorie_calculator.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class MicronutrientContent {

    @ManyToOne
    @JoinColumn(name = "micronutrient_id", nullable = false)
    private Micronutrient micronutrient;

    @Column
    private Double amountPerUnit; // Cantidad del micronutriente por unidad (gramo o microgramo)
}