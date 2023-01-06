package com.madphysicist.recipebook.services.impl;

import com.madphysicist.recipebook.model.Recipe;
import com.madphysicist.recipebook.services.RecipeService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {

    private static final Map<Integer, Recipe> recipeMap = new HashMap<>();
    private static int count = 0;

    @Override
    public void add(Recipe recipe) {
        if (count == 0) {
            count++;
            recipeMap.put(count, recipe);
            return;
        }
        if (recipeMap.containsValue(recipe)) {
            throw new RuntimeException("this recipe has already been added");
        } else {
            count++;
            recipeMap.put(count, recipe);
        }
    }

    @Override
    public Recipe get(int id) {
        if (!recipeMap.containsKey(id)) {
            throw new RuntimeException("The recipe has not found. Check ID");
        } else {
            return recipeMap.get(id);
        }
    }
}
