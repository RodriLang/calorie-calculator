package com.trainerapp.calorie_calculator.model.entity;

import com.trainerapp.calorie_calculator.enums.DifficultyType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity (name = "sections")
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "sections_ingredients",  // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "section_id"),  // Columna de Recipe
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")  // Columna de Ingredient
    )
    private List<Ingredient> ingredients;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "section_id")
    private List<Seasoning> seasonings;

    @Column(name = "description")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "section_id") // ← clave foránea en la tabla Step
    private List<Step> steps = new ArrayList<>();

    @Column(name = "preparation_time")
    Duration preparationTime;

    @Enumerated(EnumType.STRING)
    private DifficultyType difficulty;
}