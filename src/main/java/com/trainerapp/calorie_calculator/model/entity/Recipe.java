package com.trainerapp.calorie_calculator.model.entity;

import com.trainerapp.calorie_calculator.enums.DifficultyType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "meals")
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column
    private String description;
    @Column
    private String url;
    @Column
    private DifficultyType difficultyType;


    @ManyToMany
    @JoinTable(
            name = "meal_recipe",
            joinColumns = @JoinColumn(name = "meal_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id")
    )
    private List<RecipeSection> recipeSectionList;

    @ManyToMany
    @JoinTable(
            name = "meal_tag",
            joinColumns = @JoinColumn(name = "meal_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tagList;

    @Column(name = "preparation_time")
    String preparationTime;

    @Enumerated(EnumType.STRING)
    private DifficultyType difficulty;

}
