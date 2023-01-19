package com.recipe.recipebook.services;

import com.recipe.recipebook.model.Ingredient;

import java.util.Map;

public interface IngredientService {
    //добавление ингредиента в карту. Возвращает id в случае успеха.
    int add(Ingredient ingredient);

    //получение ингредиента из карты
    Ingredient get(int id);

    //замена ингредиента под данным номером в карте. Возвращает id в случае успеха.
    int change(int id, Ingredient ingredient);

    //удаление ингредиента по номеру. Возвращает id в случае успеха.
    int delete(int id);

    //получение всех ингредиентов
    Map<Integer, Ingredient> getAll();
}
