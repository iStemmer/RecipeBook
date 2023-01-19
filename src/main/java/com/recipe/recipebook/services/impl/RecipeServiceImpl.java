package com.recipe.recipebook.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.recipe.recipebook.exception.*;
import com.recipe.recipebook.model.Recipe;
import com.recipe.recipebook.services.FilesService;
import com.recipe.recipebook.services.RecipeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {

    private static Map<Integer, Recipe> recipeMap = new HashMap<>();
    private static int LastId = 0;
    @Value("${name.recipes.data}")
    private String fileName;

    private final FilesService filesService;

    public RecipeServiceImpl(FilesService filesService) {
        this.filesService = filesService;
    }

//    @PostConstruct
//    private void init() {
//        readFromFile();
//    }

    @Override
    public int add(Recipe recipe) {
        if (LastId == 0) {
            recipeMap.put(LastId, recipe);
            saveToFile();
            return LastId++;
        }
        if (recipeMap.containsValue(recipe)) {
            throw new RecipeAddException();
        }
        recipeMap.put(LastId, recipe);
        saveToFile();
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
        saveToFile();
        return id;
    }

    @Override
    public int delete(int id) {
        if (!recipeMap.containsKey(id)) {
            throw new RecipeDeleteException();
        }
        recipeMap.remove(id);
        saveToFile();
        return id;
    }

    @Override
    public Map<Integer, Recipe> getAll() {
        return recipeMap;
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipeMap);
            filesService.saveToFile(fileName, json);
        } catch (JsonProcessingException e) {
            throw new RecipeSaveToFileException();
        }
    }

    private void readFromFile() {
        try {
            String json = filesService.readFromFile(fileName);
            recipeMap = new ObjectMapper().readValue(json, new TypeReference<HashMap<Integer, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RecipeReadToFileException();
        }
    }
}
