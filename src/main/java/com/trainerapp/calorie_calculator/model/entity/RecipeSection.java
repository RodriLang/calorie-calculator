package com.trainerapp.calorie_calculator.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "recipe_sections")
public class RecipeSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;

    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Ingredient> ingredients;

    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Seasoning> seasonings;

    @ElementCollection
    @CollectionTable(name = "recipe_steps", joinColumns = @JoinColumn(name = "section_id"))
    @Column(name = "step")
    private List<String> steps;
}
