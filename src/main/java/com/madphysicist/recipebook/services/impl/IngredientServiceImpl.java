package com.madphysicist.recipebook.services.impl;

import com.madphysicist.recipebook.exception.*;
import com.madphysicist.recipebook.model.Ingredient;
import com.madphysicist.recipebook.services.IngredientService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {
    private static final Map<Integer, Ingredient> ingredientMap = new HashMap<>();
    private static int lastId = 0;

    @Override
    public int add(Ingredient ingredient) {
        if (lastId == 0) {
            ingredientMap.put(lastId, ingredient);
            return lastId++;
        }
        if (ingredientMap.containsValue(ingredient)) {
            throw new IngredientAddException();
        }
        ingredientMap.put(lastId, ingredient);
        return lastId++;
    }

    @Override
    public Ingredient get(int id) {
        if (!ingredientMap.containsKey(id)) {
            throw new IngredientGetException();
        } else {
            return ingredientMap.get(id);
        }
    }

    @Override
    public int change(int id, Ingredient ingredient) {
        if (!ingredientMap.containsKey(id)) {
            throw new IngredientChangeException();
        }
        ingredientMap.put(id, ingredient);
        return id;
    }

    @Override
    public int delete(int id) {
        if (!ingredientMap.containsKey(id)) {
            throw new IngredientDeleteException();
        }
        ingredientMap.remove(id);
        return id;
    }

    @Override
    public Map<Integer, Ingredient> getAll() {
        return ingredientMap;
    }
}
