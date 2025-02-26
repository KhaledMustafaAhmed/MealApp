package com.example.dishdash.dataLayer.model.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.dishdash.dataLayer.model.pojo.mealsList.MealsItem;

@Entity(tableName = "PlannedMeal", primaryKeys = {"user_id","meal_id", "date"})
public class PlannedMeal {
    @NonNull
    private String user_id;
    @NonNull
    private String meal_id;
    @NonNull
    private String date;
    @TypeConverters(MealConverter.class)
    private MealsItem mealsItem;

    public PlannedMeal(@NonNull String user_id, @NonNull String meal_id, String date, MealsItem mealsItem) {
        this.user_id = user_id;
        this.meal_id = meal_id;
        this.date = date;
        this.mealsItem = mealsItem;
    }

    @NonNull
    public String getUser_id() {
        return user_id;
    }

    @NonNull
    public String getMeal_id() {
        return meal_id;
    }

    public String getDate() {
        return date;
    }

    public MealsItem getMealsItem() {
        return mealsItem;
    }
}
