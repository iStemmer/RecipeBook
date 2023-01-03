package com.madphysicist.recipebook.controllers;

import com.madphysicist.recipebook.RecipeBookApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {
    @GetMapping
    public String appIsRunning(){
        return "Приложение запущено";
    }
    @GetMapping("/info")
    public String appInfo(){
        StringBuilder sb = new StringBuilder("Author name: ");
        sb.append("Oleg Kartashev").append("<br>");
        sb.append("Project name: RecipeBook").append("<br>");
        sb.append("Creation date: 11/12/2022").append("<br>");
        sb.append("Project description: My first spring project");
        return sb.toString();
    }
}
