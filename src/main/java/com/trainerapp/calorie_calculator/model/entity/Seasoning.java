package com.trainerapp.calorie_calculator.model.entity;

import com.trainerapp.calorie_calculator.enums.UnitType;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "seasonings")
public class Seasoning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String label;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private UnitType unit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id", nullable = false)
    private RecipeSection section;
}

