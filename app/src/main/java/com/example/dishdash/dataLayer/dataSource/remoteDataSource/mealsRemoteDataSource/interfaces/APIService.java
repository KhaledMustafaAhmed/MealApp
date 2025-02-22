package com.example.dishdash.dataLayer.dataSource.remoteDataSource.mealsRemoteDataSource.interfaces;

import com.example.dishdash.dataLayer.model.pojo.mealsList.MeaList;
import com.example.dishdash.dataLayer.model.pojo.popularCustomPojo.PopularList;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {
    @GET("random.php")
    public Single<MeaList> getRandoMeal();

    @GET("filter.php")
    public Observable<PopularList> getPopularItems(@Query("c") String category);

    @GET("lookup.php")
    public Single<MeaList> getMealByID(@Query("i") String mealID);
}
