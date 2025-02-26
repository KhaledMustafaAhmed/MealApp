package com.example.dishdash.dataLayer.repository.mealsRepo;

import android.util.Log;

import com.example.dishdash.dataLayer.dataSource.localDataSource.MealsLocalSourceImpl;
import com.example.dishdash.dataLayer.dataSource.remoteDataSource.mealsRemoteDataSource.classes.MealsRemoteSourceImpl;
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

public class MealsRepository implements IMealsRepository{
    private static final String TAG = "MealsRepository";
    MealsRemoteSourceImpl mealsRemoteSourceImpl;
    MealsLocalSourceImpl mealsLocalSourceImpl;
    private static MealsRepository instance = null;
    private MealsRepository(MealsRemoteSourceImpl mealsRemoteSourceImpl, MealsLocalSourceImpl mealsLocalSourceImpl){
        this.mealsRemoteSourceImpl = mealsRemoteSourceImpl;
        this.mealsLocalSourceImpl =mealsLocalSourceImpl;
    }

    public static MealsRepository getInstance(MealsRemoteSourceImpl mealsRemoteSourceImpl, MealsLocalSourceImpl mealsLocalSourceImpl) {
        if(instance == null){
            instance = new MealsRepository(mealsRemoteSourceImpl, mealsLocalSourceImpl);
        }
        return instance;
    }

    @Override
    public Single<MeaList> getRandoMeal() {
        return mealsRemoteSourceImpl.getRandoMeal();
    }

    @Override
    public Single<PopularList> getMealsBasedOnCategory(String category) {
        Log.e(TAG, "getPopularItems: in repo" );
        return mealsRemoteSourceImpl.getMealsBasedOnCategory(category) ;
    }

    @Override
    public Single<PopularList> getMealsBasedOnCountry(String country) {
        return mealsRemoteSourceImpl.getMealsBasedOnCountry(country);
    }

    @Override
    public Single<MeaList> getMealByID(String mealID) {
        return mealsRemoteSourceImpl.getMealByID(mealID);
    }

    @Override
    public Single<CategoryList> getAllCategories(String categoryCode) {
        return mealsRemoteSourceImpl.getAllCategories(categoryCode);
    }

    @Override
    public Single<CountryList> getAllCountries(String countryCode) {
        return mealsRemoteSourceImpl.getAllCountries(countryCode);
    }

    @Override
    public Completable addFavMeal(FavouriteMeal favouriteMeals) {
        return mealsLocalSourceImpl.addFavMeal(favouriteMeals);
    }

    @Override
    public Completable deleteFavMeal(String user_id, String meal_id) {
        return mealsLocalSourceImpl.deleteFavMeal(user_id,meal_id);
    }

    @Override
    public Single<List<FavouriteMeal>> getFavouriteMealsForUser(String user_id) {
        return mealsLocalSourceImpl.getFavouriteMealsForUser(user_id);
    }

    @Override
    public Completable addPlannedMeal(PlannedMeal plannedMeal) {
        return mealsLocalSourceImpl.addPlannedMeal(plannedMeal);
    }

    @Override
    public Completable deletePlannedMeal(String user_id, String meal_id, String date) {
        return mealsLocalSourceImpl.deletePlannedMeal(user_id,meal_id, date);
    }

    @Override
    public Single<List<PlannedMeal>> getPlannedMealForUser(String user_id) {
        return mealsLocalSourceImpl.getPlannedMealForUser(user_id);
    }


}
