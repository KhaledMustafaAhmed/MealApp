package com.example.dishdash.uiLayer.mealsByCountry.interfaces;

import com.example.dishdash.dataLayer.model.pojo.popularCustomPojo.PopularItem;

import java.util.List;

public interface ICountryMealsViews {
    public void receiveCountryMeals(List<PopularItem> meals);

}
