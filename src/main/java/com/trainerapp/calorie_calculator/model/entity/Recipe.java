package com.trainerapp.calorie_calculator.model.entity;

import com.trainerapp.calorie_calculator.enums.DifficultyType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "recipe_ingredients",  // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "recipe_id"),  // Columna de Recipe
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")  // Columna de Ingredient
    )
    private List<Ingredient> ingredients;

    @Column
    private String shortDescription;

    @Column
    private String instructions;

    @Column
    private String preparationTime;

    @Enumerated(EnumType.STRING)
    private DifficultyType difficulty;
}