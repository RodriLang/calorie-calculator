package com.trainerapp.calorie_calculator.model.entity;

import com.trainerapp.calorie_calculator.enums.UnitType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "measurement_units") // Especifica el nombre de la tabla
public class MeasurementUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private UnitType unit;

    @Column
    private Double gramsPerUnit;
}
