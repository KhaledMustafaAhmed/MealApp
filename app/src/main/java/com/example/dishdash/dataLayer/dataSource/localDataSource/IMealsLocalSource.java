package com.example.dishdash.dataLayer.dataSource.localDataSource;

import com.example.dishdash.dataLayer.model.entities.FavouriteMeal;
import com.example.dishdash.dataLayer.model.entities.PlannedMeal;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface IMealsLocalSource {
    public Completable addFavMeal(FavouriteMeal favouriteMeals);

    public Completable deleteFavMeal(String user_id, String meal_id);

    public Single<List<FavouriteMeal>> getFavouriteMealsForUser(String user_id);

    public Completable addPlannedMeal(PlannedMeal plannedMeal);

    public Completable deletePlannedMeal(String user_id, String meal_id);

    public Single<List<PlannedMeal>> getPlannedMealForUser(String user_id);

}
