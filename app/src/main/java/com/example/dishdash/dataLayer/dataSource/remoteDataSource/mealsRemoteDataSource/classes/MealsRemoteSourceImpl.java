package com.example.dishdash.dataLayer.dataSource.remoteDataSource.mealsRemoteDataSource.classes;

import android.util.Log;

import com.example.dishdash.dataLayer.dataSource.remoteDataSource.mealsRemoteDataSource.interfaces.APIService;
import com.example.dishdash.dataLayer.dataSource.remoteDataSource.mealsRemoteDataSource.interfaces.MealsRemoteDataSource;
import com.example.dishdash.dataLayer.model.pojo.areaCustomPojo.CountryList;
import com.example.dishdash.dataLayer.model.pojo.categoryCustomPojo.CategoryList;
import com.example.dishdash.dataLayer.model.pojo.ingredientsCustomPojo.IngredientList;
import com.example.dishdash.dataLayer.model.pojo.mealsList.MeaList;
import com.example.dishdash.dataLayer.model.pojo.popularCustomPojo.PopularList;


import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealsRemoteSourceImpl implements MealsRemoteDataSource {
    private static final String TAG = "MealsRemoteSourceImpl";

    private static final String URL = "https://www.themealdb.com/api/json/v1/1/";
    APIService apiService;
    private static MealsRemoteSourceImpl instance = null;

    private MealsRemoteSourceImpl(){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(URL)
                .build();
        apiService = retrofit.create(APIService.class);
    }

    public static MealsRemoteSourceImpl getInstance() {
        if(instance == null){
            instance = new MealsRemoteSourceImpl();
        }
        return instance;
    }

    @Override
    public Single<MeaList> getRandoMeal() {
        return apiService.getRandoMeal();
    }

    @Override
    public Single<PopularList> getMealsBasedOnCategory(String category) {
        Log.e(TAG, "getPopularItems: in remote" );
        return apiService.getMealsBasedOnCategory(category);
    }

    @Override
    public Single<PopularList> getMealsBasedOnCountry(String country) {
        return apiService.getMealsBasedOnCountry(country);
    }

    @Override
    public Single<MeaList> getMealByID(String mealID) {
        return apiService.getMealByID(mealID);
    }

    @Override
    public Single<CategoryList> getAllCategories(String categoryCode) {
        return apiService.getAllCategories(categoryCode);
    }

    @Override
    public Single<CountryList> getAllCountries(String countryCode) {
        return apiService.getAllCountries(countryCode);
    }

    @Override
    public Single<IngredientList> getAllIngredients(String ingredientCode) {
        return apiService.getAllIngredients(ingredientCode);
    }

}
