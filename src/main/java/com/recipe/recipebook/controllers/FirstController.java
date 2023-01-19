package com.recipe.recipebook.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Пробный контролер", description = "Проверка работы и инфо приложения")
public class FirstController {
    @GetMapping
    @Operation(description = "Проверка работы приложения")
    public String appIsRunning() {
        return "Приложение запущено";
    }

    @GetMapping("/info")
    @Operation(description = "Информация о приложении")
    public String appInfo() {
        return "Author name: " + "Oleg Kartashev" + "<br>" +
                "Project name: RecipeBook" + "<br>" +
                "Creation date: 11/12/2022" + "<br>" +
                "Project description: My first spring project";
    }
}
