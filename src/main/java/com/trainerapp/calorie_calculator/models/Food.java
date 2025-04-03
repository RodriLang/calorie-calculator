package com.trainerapp.calorie_calculator.models;

import com.trainerapp.calorie_calculator.enums.FoodOriginType;
import com.trainerapp.calorie_calculator.enums.NutritionalFunctionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "foods") // Especifica el nombre de la tabla
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    private FoodOriginType foodOrigin;

    @ElementCollection(targetClass = NutritionalFunctionType.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "food_nutritional_functions", joinColumns = @JoinColumn(name = "food_id"))
    private List<NutritionalFunctionType> nutritionalFunctions = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "food_id")
    private List<MeasurementUnit> measurementUnits = new ArrayList<>(); // Inicializado

    @Embedded
    private NutritionalInfo nutritionalInfo;

    @ElementCollection
    @CollectionTable(name = "micronutrient_content", joinColumns = @JoinColumn(name = "food_id"))
    private List<MicronutrientContent> micronutrients = new ArrayList<>(); // Inicializado
}