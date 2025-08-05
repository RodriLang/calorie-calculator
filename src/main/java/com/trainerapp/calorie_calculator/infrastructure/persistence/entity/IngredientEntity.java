package com.trainerapp.calorie_calculator.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ingredients") // Especifica el nombre de la tabla
public class IngredientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private FoodEntity food;

    private Double amount; //2 unidades, 100 ml, 3 cucharadas.

    private String displayName;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private MeasurementUnitEntity unit;

}
