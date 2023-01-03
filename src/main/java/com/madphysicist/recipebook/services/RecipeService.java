package com.madphysicist.recipebook.services;

import com.madphysicist.recipebook.model.Recipe;

import java.util.Map;

public interface RecipeService {
    //добавление рецепта в карту
    void add(Recipe recipe);

   //получение рецепта из карты
    Recipe get(int id);

    //замена рецепта под данным номером в карте
    void change(int id, Recipe recipe);

    //удаление рецепта по номеру
    void delete(int id);

    //получение всех рецептов
    Map<Integer, Recipe> getAll();
}
