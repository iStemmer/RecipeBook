package com.madphysicist.recipebook.services;

public interface FilesService {
    boolean saveToFile(String fileName, String json);

    String readFromFile(String fileName);
}
