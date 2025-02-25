package com.example.dishdash.dataLayer.model.entities;

import androidx.room.TypeConverter;

import com.example.dishdash.dataLayer.model.pojo.mealsList.MealsItem;
import com.google.gson.Gson;

public class MealConverter {
    @TypeConverter
    public String fromMealToString(MealsItem mealsItem){
        return new Gson().toJson(mealsItem);
    }
    @TypeConverter
    public MealsItem fromStringToMeal(String gsonStringMeal){
        return new Gson().fromJson(gsonStringMeal, MealsItem.class);
   }
}
