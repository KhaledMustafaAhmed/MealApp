package com.example.dishdash.dataLayer.repository.mealsRepo;

import com.example.dishdash.dataLayer.dataSource.remoteDataSource.mealsRemoteDataSource.classes.MealsRemoteSourceImpl;
import com.example.dishdash.dataLayer.dataSource.remoteDataSource.mealsRemoteDataSource.interfaces.MealsRemoteDataSource;
import com.example.dishdash.dataLayer.model.pojo.MeaList;

import io.reactivex.Single;

public class MealsRepository implements IMealsRepository{
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
}
