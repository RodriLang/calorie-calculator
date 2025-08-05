package com.trainerapp.calorie_calculator.infrastructure.persistence.entity;

import com.trainerapp.calorie_calculator.domain.enums.UnitType;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "seasonings") // Especifica el nombre de la tabla
public class SeasoningEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private UUID publicId;

    @Column
    private String name;

    @Column
    private Double amount;

    @Enumerated(EnumType.STRING)
    private UnitType unit;

    @Column
    private String label;
}
