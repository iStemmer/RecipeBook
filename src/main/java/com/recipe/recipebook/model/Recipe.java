package com.recipe.recipebook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
    @NonNull
    private String name;
    @NonNull
    private int preparingTime;
    @NonNull
    private List<Ingredient> ingredients;
    @NonNull
    private List<String> steps;
}
