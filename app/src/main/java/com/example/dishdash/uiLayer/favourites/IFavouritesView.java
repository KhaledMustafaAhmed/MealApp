package com.example.dishdash.uiLayer.favourites;

import com.example.dishdash.dataLayer.model.entities.FavouriteMeal;
import com.example.dishdash.dataLayer.model.pojo.mealsList.MealsItem;

import java.util.List;

public interface IFavouritesView {
    public void receiveFavouritesItem(List<FavouriteMeal> favouriteMeals) ;

    public void deletionSuccess();
}
