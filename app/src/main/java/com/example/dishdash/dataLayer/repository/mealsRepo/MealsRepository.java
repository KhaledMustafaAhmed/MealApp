package com.example.dishdash.dataLayer.repository.mealsRepo;

import android.util.Log;

import com.example.dishdash.dataLayer.dataSource.remoteDataSource.mealsRemoteDataSource.classes.MealsRemoteSourceImpl;
import com.example.dishdash.dataLayer.model.pojo.areaCustomPojo.CountryList;
import com.example.dishdash.dataLayer.model.pojo.categoryCustomPojo.CategoryList;
import com.example.dishdash.dataLayer.model.pojo.mealsList.MeaList;
import com.example.dishdash.dataLayer.model.pojo.popularCustomPojo.PopularList;

import io.reactivex.Observable;
import io.reactivex.Single;

public class MealsRepository implements IMealsRepository{
    private static final String TAG = "MealsRepository";
    MealsRemoteSourceImpl mealsRemoteSourceImpl;
    private static MealsRepository instance = null;
    private MealsRepository(MealsRemoteSourceImpl mealsRemoteSourceImpl){
        this.mealsRemoteSourceImpl = mealsRemoteSourceImpl;
    }

    public static MealsRepository getInstance(MealsRemoteSourceImpl mealsRemoteSourceImpl) {
        if(instance == null){
            instance = new MealsRepository(mealsRemoteSourceImpl);
        }
        return instance;
    }

    @Override
    public Single<MeaList> getRandoMeal() {
        return mealsRemoteSourceImpl.getRandoMeal();
    }

    @Override
    public Observable<PopularList> getPopularItems(String category) {
        Log.e(TAG, "getPopularItems: in repo" );
        return mealsRemoteSourceImpl.getPopularItems(category) ;
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


}
