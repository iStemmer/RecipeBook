package com.madphysicist.recipebook.services;

import com.madphysicist.recipebook.model.Ingredient;

public interface IngredientService {
    //добавление ингредиента в карту
    void add(Ingredient ingredient);

    //получение ингредиента из карты
    Ingredient get(int id);
}
