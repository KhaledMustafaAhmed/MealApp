package com.example.dishdash.dataLayer.dataSource.remoteDataSource.mealsRemoteDataSource.interfaces;

import com.example.dishdash.dataLayer.model.pojo.mealsList.MeaList;
import com.example.dishdash.dataLayer.model.pojo.popularCustomPojo.PopularList;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.Query;

public interface MealsRemoteDataSource {
    public Single<MeaList> getRandoMeal();

    public Observable<PopularList> getPopularItems(String category);

    public Single<MeaList> getMealByID(String mealID);

}
