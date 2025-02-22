package com.example.dishdash.dataLayer.repository.mealsRepo;

import com.example.dishdash.dataLayer.model.pojo.mealsList.MeaList;
import com.example.dishdash.dataLayer.model.pojo.popularCustomPojo.PopularList;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface IMealsRepository {
    public Single<MeaList> getRandoMeal();

    public Observable<PopularList> getPopularItems(String category);

    public Single<MeaList> getMealByID(String mealID);
}
