package com.madphysicist.recipebook.services;

import com.madphysicist.recipebook.model.Ingredient;

import java.util.Map;

public interface IngredientService {
    //добавление ингредиента в карту
    void add(Ingredient ingredient);

    //получение ингредиента из карты
    Ingredient get(int id);

    //замена ингредиента под данным номером в карте
    void change(int id, Ingredient ingredient);

    //удаление ингредиента по номеру
    void delete(int id);

    //получение всех ингредиентов
    Map<Integer, Ingredient> getAll();
}
