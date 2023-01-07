package com.madphysicist.recipebook.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {
    @NonNull
    private String name;
    @NonNull
    private int quantity;
    @NonNull
    private String unit;
}
