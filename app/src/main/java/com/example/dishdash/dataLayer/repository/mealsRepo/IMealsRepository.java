package com.example.dishdash.dataLayer.repository.mealsRepo;

import com.example.dishdash.dataLayer.model.pojo.MeaList;

import io.reactivex.Single;

public interface IMealsRepository {
    public Single<MeaList> getRandoMeal();
}
