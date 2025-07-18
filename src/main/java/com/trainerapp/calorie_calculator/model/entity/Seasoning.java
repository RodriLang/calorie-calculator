package com.trainerapp.calorie_calculator.model.entity;

import com.trainerapp.calorie_calculator.enums.UnitType;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "seasonings") // Especifica el nombre de la tabla
public class Seasoning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Double amount;

    @Enumerated(EnumType.STRING)
    private UnitType unit;

    @Column
    private String label;
}
