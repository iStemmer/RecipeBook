package com.madphysicist.recipebook.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.madphysicist.recipebook.exception.*;
import com.madphysicist.recipebook.model.Ingredient;
import com.madphysicist.recipebook.services.FilesService;
import com.madphysicist.recipebook.services.IngredientService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {
    private static Map<Integer, Ingredient> ingredientMap = new HashMap<>();
    private static int lastId = 0;

    @Value("${name.ingredients.data}")
    private String fileName;

    private final FilesService filesService;

    public IngredientServiceImpl(FilesService filesService) {
        this.filesService = filesService;
    }

    @PostConstruct
    private void init() {
        readFromFile();
    }

    @Override
    public int add(Ingredient ingredient) {
        if (lastId == 0) {
            ingredientMap.put(lastId, ingredient);
            saveToFile();
            return lastId++;
        }
        if (ingredientMap.containsValue(ingredient)) {
            throw new IngredientAddException();
        }
        ingredientMap.put(lastId, ingredient);
        saveToFile();
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
        saveToFile();
        return id;
    }

    @Override
    public int delete(int id) {
        if (!ingredientMap.containsKey(id)) {
            throw new IngredientDeleteException();
        }
        ingredientMap.remove(id);
        saveToFile();
        return id;
    }

    @Override
    public Map<Integer, Ingredient> getAll() {
        return ingredientMap;
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredientMap);
            filesService.saveToFile(fileName, json);
        } catch (JsonProcessingException e) {
            throw new IngredientSaveToFileException();
        }
    }

    private void readFromFile() {
        try {
            String json = filesService.readFromFile(fileName);
            ingredientMap = new ObjectMapper().readValue(json, new TypeReference<HashMap<Integer, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new IngredientReadToFileException();
        }
    }
}
