package com.madphysicist.recipebook.controllers;

import com.madphysicist.recipebook.model.Recipe;
import com.madphysicist.recipebook.services.RecipeService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/{id}")
    public Recipe getRecipe(@PathVariable int id) {
        return recipeService.get(id);
    }

    @GetMapping("/all")
    public Map<Integer, Recipe> getAllRecipes() {
        return recipeService.getAll();
    }

    @PostMapping
    public void addRecipe(@RequestBody Recipe recipe) {
        recipeService.add(recipe);
    }

    @PutMapping("/{id}")
    public void changeRecipe(@PathVariable int id, @RequestBody Recipe recipe) {
        recipeService.change(id, recipe);
    }

    @DeleteMapping("/{id}")
    public void deleteRecipe(@PathVariable int id) {
        recipeService.delete(id);
    }
}
