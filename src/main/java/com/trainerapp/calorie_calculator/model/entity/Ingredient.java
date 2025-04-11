package com.trainerapp.calorie_calculator.model.entity;

import com.trainerapp.calorie_calculator.enums.UnitType;
import jakarta.persistence.*;
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
@Table(name = "ingredients") // Especifica el nombre de la tabla
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

    private Double quantity; //2 unidades, 100 ml, 3 cucharadas.

    @Enumerated(EnumType.STRING)
    private UnitType unit;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "ingredients")  // Relación inversa
    private List<Recipe> recipes;
}
