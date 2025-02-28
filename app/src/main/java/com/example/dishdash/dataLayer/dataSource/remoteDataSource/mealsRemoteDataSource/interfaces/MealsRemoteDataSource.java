package com.example.dishdash.dataLayer.dataSource.remoteDataSource.mealsRemoteDataSource.interfaces;

import com.example.dishdash.dataLayer.model.pojo.areaCustomPojo.CountryList;
import com.example.dishdash.dataLayer.model.pojo.categoryCustomPojo.CategoryList;
import com.example.dishdash.dataLayer.model.pojo.ingredientsCustomPojo.IngredientList;
import com.example.dishdash.dataLayer.model.pojo.mealsList.MeaList;
import com.example.dishdash.dataLayer.model.pojo.popularCustomPojo.PopularList;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.Query;

public interface MealsRemoteDataSource {
    public Single<MeaList> getRandoMeal();

    public Single<PopularList> getMealsBasedOnCategory(String category);

    public Single<PopularList> getMealsBasedOnCountry(String country);

    public Single<MeaList> getMealByID(String mealID);

    public Single<CategoryList> getAllCategories(String categoryCode);

    public Single<CountryList> getAllCountries(String countryCode);

    public Single<IngredientList> getAllIngredients(String ingredientCode);


}
