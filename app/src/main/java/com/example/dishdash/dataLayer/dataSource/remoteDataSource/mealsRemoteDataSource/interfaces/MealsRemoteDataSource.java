package com.example.dishdash.dataLayer.dataSource.remoteDataSource.mealsRemoteDataSource.interfaces;

import com.example.dishdash.dataLayer.model.pojo.MeaList;

import io.reactivex.Single;

public interface MealsRemoteDataSource {
    public Single<MeaList> getRandoMeal();
}
