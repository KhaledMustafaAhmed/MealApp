package com.example.dishdash.dataLayer.dataSource.remoteDataSource.mealsRemoteDataSource.classes;

import android.util.Log;

import com.example.dishdash.dataLayer.dataSource.remoteDataSource.mealsRemoteDataSource.interfaces.APIService;
import com.example.dishdash.dataLayer.dataSource.remoteDataSource.mealsRemoteDataSource.interfaces.MealsRemoteDataSource;
import com.example.dishdash.dataLayer.model.pojo.mealsList.MeaList;
import com.example.dishdash.dataLayer.model.pojo.popularCustomPojo.PopularList;


import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealsRemoteSourceImpl implements MealsRemoteDataSource {
    private static final String TAG = "MealsRemoteSourceImpl";

    private static final String URL = "https://www.themealdb.com/api/json/v1/1/";
    APIService apiService;
    private static MealsRemoteSourceImpl instance = null;

    private MealsRemoteSourceImpl(){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(URL)
                .build();
        apiService = retrofit.create(APIService.class);
    }

    public static MealsRemoteSourceImpl getInstance() {
        if(instance == null){
            instance = new MealsRemoteSourceImpl();
        }
        return instance;
    }

    @Override
    public Single<MeaList> getRandoMeal() {
        return apiService.getRandoMeal();
    }

    @Override
    public Observable<PopularList> getPopularItems(String category) {
        Log.e(TAG, "getPopularItems: in remote" );
        return apiService.getPopularItems(category);
    }

    @Override
    public Single<MeaList> getMealByID(String mealID) {
        return apiService.getMealByID(mealID);
    }

}
