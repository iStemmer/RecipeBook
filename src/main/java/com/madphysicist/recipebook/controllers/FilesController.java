package com.madphysicist.recipebook.controllers;

import com.madphysicist.recipebook.services.FilesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/files")
@Tag(name = "Файлы", description = "Эндпойнты для работы с файлами")
public class FilesController {

    @Value("${name.recipes.data}")
    private String recipeFileName;

    @Value("${name.ingredients.data}")
    private String ingredientFileName;

    private final FilesService filesService;

    public FilesController(FilesService filesService) {
        this.filesService = filesService;
    }

    @GetMapping(value = "/export", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Получение файла с данными всех рецептов")
    public ResponseEntity<InputStreamResource> downloadRecipesData() throws FileNotFoundException {
        File file = filesService.getDataFile(recipeFileName);
        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename = \"Recipes.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/recipes_import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(description = "Отправка и замена файла с данными рецептов")
    public ResponseEntity<Void> uploadRecipes(@RequestParam MultipartFile file) {
        return getVoidResponseEntity(file, recipeFileName);
    }

    @PostMapping(value ="/ingredients_import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(description = "Отправка и замена файла с данными ингредиентов")
    public ResponseEntity<Void> uploadIngredients(@RequestParam MultipartFile file) {
        return getVoidResponseEntity(file, ingredientFileName);
    }

    private ResponseEntity<Void> getVoidResponseEntity(@RequestParam MultipartFile file, String FileName) {
        filesService.cleanDataFile(FileName);
        File dataFile = filesService.getDataFile(FileName);

        try (FileOutputStream fos = new FileOutputStream(dataFile)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
