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

    @Override
    public void change(int id, Recipe recipe) {
        if (!recipeMap.containsKey(id)) {
            throw new RuntimeException("Changeable recipe has not found. Check ID");
        }
        recipeMap.put(id, recipe);
    }

    @Override
    public void delete(int id) {
        if (!recipeMap.containsKey(id)) {
            throw new RuntimeException("Deletable recipe has not found. Check ID");
        }
        recipeMap.remove(id);
    }


    @Override
    public Map<Integer, Recipe> getAll() {
        return recipeMap;
    }
}
