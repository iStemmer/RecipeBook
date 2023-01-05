package com.madphysicist.recipebook.controllers;

import com.madphysicist.recipebook.exception.*;
import com.madphysicist.recipebook.model.Ingredient;
import com.madphysicist.recipebook.services.IngredientService;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public Map<Integer, Ingredient> getAllIngredients() {
        return ingredientService.getAll();
    }

    @PostMapping
    public ResponseEntity<?> addIngredient(@RequestBody Ingredient ingredient) {
        try {
            int result = ingredientService.add(ingredient);
            return ResponseEntity.ok(result);
        } catch (IngredientAddException e) {
            return ResponseEntity.badRequest().body(ingredient);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> changeRecipe(@PathVariable int id, @RequestBody Ingredient ingredient) {
        try {
            int result = ingredientService.change(id, ingredient);
            return ResponseEntity.ok(result);
        } catch (IngredientChangeException e) {
            return ResponseEntity.badRequest().body(id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteIngredient(@PathVariable int id) {
        try {
            int result = ingredientService.delete(id);
            return ResponseEntity.ok(result);
        } catch (IngredientDeleteException e) {
            return ResponseEntity.badRequest().body(id);
        }
    }
}
