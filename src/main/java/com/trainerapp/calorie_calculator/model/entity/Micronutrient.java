package com.trainerapp.calorie_calculator.model.entity;

import com.trainerapp.calorie_calculator.enums.MicronutrientType;
import com.trainerapp.calorie_calculator.enums.UnitType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "micronutrients") // Especifica el nombre de la tabla
public class Micronutrient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private Double rda;

    @Enumerated(EnumType.STRING)
    private UnitType unit;

    @Enumerated(EnumType.STRING)
    private MicronutrientType type;
}
