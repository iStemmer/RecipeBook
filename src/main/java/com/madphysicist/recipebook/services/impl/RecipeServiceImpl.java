package com.madphysicist.recipebook.services.impl;

import com.madphysicist.recipebook.exception.*;
import com.madphysicist.recipebook.model.Recipe;
import com.madphysicist.recipebook.services.RecipeService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {

    private static final Map<Integer, Recipe> recipeMap = new HashMap<>();
    private static int LastId = 0;

    @Override
    public int add(Recipe recipe) {
        if (LastId == 0) {
            recipeMap.put(LastId, recipe);
            return LastId++;
        }
        if (recipeMap.containsValue(recipe)) {
            throw new RecipeAddException();
        }
        recipeMap.put(LastId, recipe);
        return LastId++;
    }

    @Override
    public Recipe get(int id) {
        if (!recipeMap.containsKey(id)) {
            throw new RecipeGetException();
        } else {
            return recipeMap.get(id);
        }
    }

    @Override
    public int change(int id, Recipe recipe) {
        if (!recipeMap.containsKey(id)) {
            throw new RecipeChangeException();
        }
        recipeMap.put(id, recipe);
        return id;
    }

    @Override
    public int delete(int id) {
        if (!recipeMap.containsKey(id)) {
            throw new RecipeDeleteException();
        }
        recipeMap.remove(id);
        return id;
    }

    @Override
    public Map<Integer, Recipe> getAll() {
        return recipeMap;
    }
}
