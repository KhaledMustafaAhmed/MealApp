package com.example.dishdash.dataLayer.dataSource.remoteDataSource.mealsRemoteDataSource.interfaces;

import com.example.dishdash.dataLayer.model.pojo.areaCustomPojo.CountryList;
import com.example.dishdash.dataLayer.model.pojo.categoryCustomPojo.CategoryList;
import com.example.dishdash.dataLayer.model.pojo.ingredientsCustomPojo.IngredientList;
import com.example.dishdash.dataLayer.model.pojo.mealsList.MeaList;
import com.example.dishdash.dataLayer.model.pojo.popularCustomPojo.PopularList;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {
    @GET("random.php")
    public Single<MeaList> getRandoMeal();

    @GET("filter.php")
    public Single<PopularList> getMealsBasedOnCategory(@Query("c") String category);

    @GET("filter.php")
    public Single<PopularList> getMealsBasedOnCountry(@Query("a") String country);

    @GET("lookup.php")
    public Single<MeaList> getMealByID(@Query("i") String mealID);

    @GET("list.php")
    public Single<CategoryList> getAllCategories(@Query("c") String categoryCode);


    @GET("list.php")
    public Single<CountryList> getAllCountries(@Query("a") String countryCode);

    @GET("list.php")
    public Single<IngredientList> getAllIngredients(@Query("i") String ingredientCode);

}
