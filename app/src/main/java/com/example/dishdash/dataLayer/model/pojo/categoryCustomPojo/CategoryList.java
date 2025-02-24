package com.example.dishdash.dataLayer.model.pojo.categoryCustomPojo;

import java.util.List;

public class CategoryList {
    public List<CategoryItem> meals;

    public List<CategoryItem> getMeals() {
        return meals;
    }

    public void setMeals(List<CategoryItem> meals) {
        this.meals = meals;
    }

    public int getSize(){
        return  meals.size();
    }
}
