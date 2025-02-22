package com.example.dishdash.dataLayer.dataSource.remoteDataSource.mealsRemoteDataSource.interfaces;

import com.example.dishdash.dataLayer.model.pojo.MeaList;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("random.php")
    public Single<MeaList> getRandoMeal();
}
