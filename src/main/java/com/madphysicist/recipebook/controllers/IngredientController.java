package com.madphysicist.recipebook.controllers;

import com.madphysicist.recipebook.model.Ingredient;
import com.madphysicist.recipebook.services.IngredientService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    private IngredientService ingredientService;

    public IngredientController(IngredientService recipeService) {
        this.ingredientService = recipeService;
    }

    @GetMapping("/{id}")
    public Ingredient getIngredient(int id) {
        return ingredientService.get(id);
    }

    @PostMapping
    public void addRecipe(@RequestBody Ingredient ingredient) {
        ingredientService.add(ingredient);
    }
}
