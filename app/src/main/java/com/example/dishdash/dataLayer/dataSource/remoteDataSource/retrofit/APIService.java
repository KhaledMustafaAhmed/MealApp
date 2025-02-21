package com.example.dishdash.dataLayer.dataSource.remoteDataSource.retrofit;

import com.example.dishdash.dataLayer.model.pojo.MeaList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("random.php")
    public Call<MeaList> getRandoMeal();
}
