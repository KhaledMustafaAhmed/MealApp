package com.example.dishdash.uiLayer.mealDetails;

import com.example.dishdash.dataLayer.model.entities.PlannedMeal;
import com.example.dishdash.dataLayer.model.pojo.mealsList.MealsItem;

public interface MealDetailsContract {
    public void getMeal(String mealID);

    public void addMealToFavourites(String user_id, MealsItem mealsItem);

    public void addFavouriteMealToRemote(String user_id, MealsItem mealsItem);

    public String getUserID();

    public String calcDate(int year, int monthOfYear, int dayOfMonth);

    public void addMealToWeeklyPlan(MealsItem mealsItem, String date);
}
