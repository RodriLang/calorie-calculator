package com.trainerapp.calorie_calculator.model.entity;

import com.trainerapp.calorie_calculator.enums.MicronutrientType;
import com.trainerapp.calorie_calculator.enums.UnitType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity@Table(name = "micronutrients") // Especifica el nombre de la tabla
public class Micronutrient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private MicronutrientType name;

    @Column
    private Double dailyAmount;

    @Enumerated(EnumType.STRING)
    private UnitType unit;

}
