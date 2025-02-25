package com.example.dishdash.dataLayer.model.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.TypeConverters;

import com.example.dishdash.dataLayer.model.pojo.mealsList.MealsItem;

@Entity(tableName = "FavouriteMeals", primaryKeys = {"meal_id", "user_id"})
public class FavouriteMeal {
    @NonNull
    private String meal_id;
    @NonNull
    private String user_id;
    @TypeConverters(MealConverter.class)
    private MealsItem mealsItem;

    public FavouriteMeal(String meal_id, String user_id, MealsItem mealsItem) {
        this.meal_id = meal_id;
        this.user_id = user_id;
        this.mealsItem = mealsItem;
    }

    public String getMeal_id() {
        return meal_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public MealsItem getMealsItem() {
        return mealsItem;
    }
}
