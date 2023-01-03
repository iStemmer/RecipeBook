package com.madphysicist.recipebook.services.impl;

import com.madphysicist.recipebook.model.Ingredient;
import com.madphysicist.recipebook.services.IngredientService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {
    private static final Map<Integer, Ingredient> ingredientMap = new HashMap<>();
    private static int count = 0;

    @Override
    public void add(Ingredient ingredient) {
        if (count == 0) {
            count++;
            ingredientMap.put(count, ingredient);
            return;
        }
        if (ingredientMap.containsValue(ingredient)) {
            throw new RuntimeException("this ingredient has already been added");
        } else {
            count++;
            ingredientMap.put(count, ingredient);
        }
    }

    @Override
    public Ingredient get(int id) {
        if (!ingredientMap.containsKey(id)) {
            throw new RuntimeException("The ingredient has not found. Check ID");
        } else {
            return ingredientMap.get(id);
        }
    }

    @Override
    public void change(int id, Ingredient ingredient) {
        if (!ingredientMap.containsKey(id)) {
            throw new RuntimeException("Changeable ingredient has not found. Check ID");
        }
        ingredientMap.put(id, ingredient);
    }

    @Override
    public void delete(int id) {
        if (!ingredientMap.containsKey(id)) {
            throw new RuntimeException("Deletable ingredient has not found. Check ID");
        }
        ingredientMap.remove(id);
    }

    @Override
    public Map<Integer, Ingredient> getAll() {
        return  ingredientMap;
    }
}
