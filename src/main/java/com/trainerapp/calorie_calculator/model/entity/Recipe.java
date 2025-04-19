package com.trainerapp.calorie_calculator.model.entity;

import com.trainerapp.calorie_calculator.enums.DifficultyType;
import com.trainerapp.calorie_calculator.enums.FlavorType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Table(name = "recipes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    private String url;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeSection> sections;

    @Column(nullable = false)
    private String preparationTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DifficultyType difficultyType;

    @ManyToMany
    @JoinTable(
            name = "recipe_tag",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;

@Enumerated(EnumType.STRING)
    private FlavorType flavorType;
}


