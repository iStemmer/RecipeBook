package com.madphysicist.recipebook.controllers;

import com.madphysicist.recipebook.exception.RecipeAddException;
import com.madphysicist.recipebook.exception.RecipeChangeException;
import com.madphysicist.recipebook.exception.RecipeDeleteException;
import com.madphysicist.recipebook.model.Recipe;
import com.madphysicist.recipebook.services.RecipeService;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public Map<Integer, Recipe> getAllRecipes() {
        return recipeService.getAll();
    }

    @PostMapping
    public ResponseEntity<?> addRecipe(@RequestBody Recipe recipe) {
        try {
            int result = recipeService.add(recipe);
            return ResponseEntity.ok(result);
        } catch (RecipeAddException e) {
            return ResponseEntity.badRequest().body(recipe);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> changeRecipe(@PathVariable int id, @RequestBody Recipe recipe) {
        try {
            int result = recipeService.change(id, recipe);
            return ResponseEntity.ok(result);
        } catch (RecipeChangeException e) {
            return ResponseEntity.badRequest().body(id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteRecipe(@PathVariable int id) {
        try {
            int result = recipeService.delete(id);
            return ResponseEntity.ok(result);
        } catch (RecipeDeleteException e) {
            return ResponseEntity.badRequest().body(id);
        }
    }
}
