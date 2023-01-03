package com.madphysicist.recipebook.model;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    @NonNull
    private String name;
    @NonNull
    private int preparingTime;
    @NonNull
    private List<Ingredient> ingredients;
    @NonNull
    private ArrayList<String> steps;

    public Recipe(String name, int preparingTime, List<Ingredient> ingredients, ArrayList<String> steps) {
        this.name = name;
        this.preparingTime = preparingTime;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPreparingTime() {
        return preparingTime;
    }

    public void setPreparingTime(int preparingTime) {
        this.preparingTime = preparingTime;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<String> steps) {
        this.steps = steps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recipe recipe)) return false;

        if (preparingTime != recipe.preparingTime) return false;
        if (!name.equals(recipe.name)) return false;
        if (!ingredients.equals(recipe.ingredients)) return false;
        return steps.equals(recipe.steps);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + preparingTime;
        result = 31 * result + ingredients.hashCode();
        result = 31 * result + steps.hashCode();
        return result;
    }
}
