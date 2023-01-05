package com.madphysicist.recipebook.services;

import com.madphysicist.recipebook.model.Recipe;

import java.util.Map;

public interface RecipeService {
    //добавление рецепта в карту. Возвращает id в случае успеха.
    int add(Recipe recipe);

   //получение рецепта из карты
    Recipe get(int id);

    //замена рецепта под данным номером в карте. Возвращает id в случае успеха.
    int change(int id, Recipe recipe);

    //удаление рецепта по номеру. Возвращает id в случае успеха.
    int delete(int id);

    //получение всех рецептов
    Map<Integer, Recipe> getAll();
}
