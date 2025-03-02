package com.example.dishdash.uiLayer.mealsByIngredients.interfaces;

import com.example.dishdash.dataLayer.model.pojo.popularCustomPojo.PopularItem;

import java.util.List;

public interface IngredientMealsView {
    public void receiveIngredientMeals(List<PopularItem> meals);

}
