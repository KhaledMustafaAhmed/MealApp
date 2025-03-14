package com.example.dishdash.uiLayer.mealDetails;

import com.example.dishdash.dataLayer.model.pojo.mealsList.MealsItem;

import java.util.List;

public interface IMealDetailsView {
    public void getMealDetails(MealsItem meal);

    public void showInsetFavSuccess();
    public void showInsetFavFailed();

    public void showAddedMealPlanSuccess();

    public void showAddedMealPlanFailed();

    public void saveToFavourites(String user_id);

    public void savePlannedMeal(String user_id);

    public void inGuestMode();
}
