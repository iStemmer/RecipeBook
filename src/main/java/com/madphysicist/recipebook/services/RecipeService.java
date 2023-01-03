package com.madphysicist.recipebook.services;

import com.madphysicist.recipebook.model.Recipe;

public interface RecipeService {
    //добавление рецепта в карту
    void add(Recipe recipe);

    //получение рецепта из карты
    Recipe get(int id);
}
