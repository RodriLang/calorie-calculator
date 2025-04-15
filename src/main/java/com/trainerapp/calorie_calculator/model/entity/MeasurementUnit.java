package com.trainerapp.calorie_calculator.model.entity;

import com.trainerapp.calorie_calculator.enums.UnitType;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "measurement_units") // Especifica el nombre de la tabla
public class MeasurementUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id", nullable = false)
    private Food food;

    @Enumerated(EnumType.STRING)
    private UnitType unit;

    @Column
    private Double gramsPerUnit;
}
