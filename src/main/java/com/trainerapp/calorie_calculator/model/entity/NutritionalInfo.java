package com.trainerapp.calorie_calculator.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class NutritionalInfo {

    @NotNull
    @Min(0)
    @Column(name = "energy")
    private Double energy = 0.0; // kcal

    @NotNull
    @Min(0)
    @Column(name = "carbohydrates")
    private Double carbohydrates = 0.0; // g

    @NotNull
    @Min(0)
    @Column(name = "sugars")
    private Double sugars = 0.0; // g

    @NotNull
    @Min(0)
    @Column(name = "protein")
    private Double protein = 0.0; // g

    @NotNull
    @Min(0)
    @Column(name = "total_fat")
    private Double totalFat = 0.0; // g

    @NotNull
    @Min(0)
    @Column(name = "saturated_fat")
    private Double saturatedFat = 0.0; // g

    @NotNull
    @Min(0)
    @Column(name = "fiber")
    private Double fiber = 0.0; // g
}