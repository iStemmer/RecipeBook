package com.madphysicist.recipebook.controllers;

import com.madphysicist.recipebook.exception.RecipeAddException;
import com.madphysicist.recipebook.exception.RecipeChangeException;
import com.madphysicist.recipebook.exception.RecipeDeleteException;
import com.madphysicist.recipebook.model.Recipe;
import com.madphysicist.recipebook.services.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/recipe")
@Tag(name = "Рецепты", description = "Эндпойнты для работы с рецептами")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/{id}")
    @Operation(description = "Получение рецепта по id")
//  @ApiResponses(value = {  //я вообще не понял нужно ли это. В браузере и так отображается "схема".
//          @ApiResponse(
//                  responseCode = "200",
//                  description = "Рецепт был найден",
//                  content = {
//                          @Content(
//                                  mediaType = "application/json",
//                                  schema = @Schema(implementation = Recipe.class) //как это писать для Map<Integer, Recipe>?
//                          )
//                  }
//          )
//  })
    public ResponseEntity<Recipe> getRecipe(@PathVariable int id) {
        return ResponseEntity.ok(recipeService.get(id));
    }

    @GetMapping
    @Operation(description = "Получение всех рецептов")
    public Map<Integer, Recipe> getAllRecipes() {
        return recipeService.getAll();
    }

    @PostMapping
    @Operation(description = "Добавление рецепта")
    public ResponseEntity<?> addRecipe(@RequestBody Recipe recipe) {
        try {
            int result = recipeService.add(recipe);
            return ResponseEntity.ok(result);
        } catch (RecipeAddException e) {
            return ResponseEntity.badRequest().body(recipe);
        }
    }

    @PutMapping("/{id}")
    @Operation(description = "Изменение рецепта с указанным id")
    public ResponseEntity<Integer> changeRecipe(@PathVariable int id, @RequestBody Recipe recipe) {
        try {
            int result = recipeService.change(id, recipe);
            return ResponseEntity.ok(result);
        } catch (RecipeChangeException e) {
            return ResponseEntity.badRequest().body(id);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Удаление рецепта по id")
    public ResponseEntity<Integer> deleteRecipe(@PathVariable int id) {
        try {
            int result = recipeService.delete(id);
            return ResponseEntity.ok(result);
        } catch (RecipeDeleteException e) {
            return ResponseEntity.badRequest().body(id);
        }
    }
}
