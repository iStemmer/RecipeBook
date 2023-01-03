package com.madphysicist.recipebook.controllers;

import com.madphysicist.recipebook.model.Ingredient;
import com.madphysicist.recipebook.services.IngredientService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService recipeService) {
        this.ingredientService = recipeService;
    }

    @GetMapping("/{id}")
    public Ingredient getIngredient(@PathVariable int id) {
        return ingredientService.get(id);
    }

    @GetMapping("/all")
    public Map<Integer, Ingredient> getAllIngredients() {
        return ingredientService.getAll();
    }

    @PostMapping
    public void addRecipe(@RequestBody Ingredient ingredient) {
        ingredientService.add(ingredient);
    }

    @PutMapping("/{id}")
    public void changeRecipe(@PathVariable int id, @RequestBody Ingredient ingredient) {
        ingredientService.change(id, ingredient);
    }
    @DeleteMapping("/{id}")
    public void deleteIngredient(@PathVariable int id){
        ingredientService.delete(id);
    }
}
