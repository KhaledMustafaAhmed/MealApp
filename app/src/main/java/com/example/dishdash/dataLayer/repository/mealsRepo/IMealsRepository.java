package com.example.dishdash.dataLayer.repository.mealsRepo;

import com.example.dishdash.dataLayer.model.entities.FavouriteMeal;
import com.example.dishdash.dataLayer.model.entities.PlannedMeal;
import com.example.dishdash.dataLayer.model.pojo.areaCustomPojo.CountryList;
import com.example.dishdash.dataLayer.model.pojo.categoryCustomPojo.CategoryList;
import com.example.dishdash.dataLayer.model.pojo.mealsList.MeaList;
import com.example.dishdash.dataLayer.model.pojo.popularCustomPojo.PopularList;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface IMealsRepository {
    public Single<MeaList> getRandoMeal();

    public Single<PopularList> getMealsBasedOnCategory(String category);

    public Single<PopularList> getMealsBasedOnCountry(String country);


    public Single<MeaList> getMealByID(String mealID);

    public Single<CategoryList> getAllCategories(String categoryCode);

    public Single<CountryList> getAllCountries(String countryCode);


    public Completable addFavMeal(FavouriteMeal favouriteMeals);

    public Completable deleteFavMeal(String user_id, String meal_id);

    public Single<List<FavouriteMeal>> getFavouriteMealsForUser(String user_id);

    public Completable addPlannedMeal(PlannedMeal plannedMeal);

    public Completable deletePlannedMeal(String user_id, String meal_id, String date);

    public Single<List<PlannedMeal>> getPlannedMealForUser(String user_id);
}
