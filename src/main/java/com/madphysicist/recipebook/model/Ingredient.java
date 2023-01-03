package com.madphysicist.recipebook.model;


import org.springframework.lang.NonNull;

public class Ingredient {
    @NonNull
    private String name;
    @NonNull
    private int quantity;
    @NonNull
    private String unit;

    public Ingredient(String name, int quantity, String unit) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ingredient that)) return false;

        if (quantity != that.quantity) return false;
        if (!name.equals(that.name)) return false;
        return unit.equals(that.unit);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + quantity;
        result = 31 * result + unit.hashCode();
        return result;
    }
}
