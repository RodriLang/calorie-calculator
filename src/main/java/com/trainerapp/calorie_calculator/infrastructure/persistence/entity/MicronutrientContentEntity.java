package com.trainerapp.calorie_calculator.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class MicronutrientContentEntity {

    @Column(nullable = false, unique = true)
    private UUID publicId;

    @ManyToOne
    @JoinColumn(name = "micronutrient_id", nullable = false)
    private MicronutrientEntity micronutrient;

    @Column
    private Double amountPerUnit; // Cantidad del micronutriente por unidad (gramo o microgramo)
}