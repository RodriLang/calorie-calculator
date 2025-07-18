package com.trainerapp.calorie_calculator.model.entity;

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
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column
    private String imageUrl;

    @Column
    private String preparationTime;

    @Column
    private Integer portions;

    @ManyToMany
    @JoinTable(
            name = "recipes_sections",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "section_id")
    )
    private List<Section> sections;

    @ManyToMany
    @JoinTable(
            name = "recipes_tags",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tagList;

}