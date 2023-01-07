package com.madphysicist.recipebook.services;

import java.io.File;

public interface FilesService {
    boolean saveToFile(String fileName, String json);

    String readFromFile(String fileName);

    File getDataFile(String filensme);

    boolean cleanDataFile(String fileName);
}
