package com.example.dishdash.uiLayer.mealsByCategory.interfaces;

import com.example.dishdash.dataLayer.model.pojo.popularCustomPojo.PopularItem;

import java.util.List;

public interface ICategoryMealsView {
    public void receiveCategoryMeals(List<PopularItem> meals);
}
