package com.example.dishdash.dataLayer.dataSource.remoteDataSource.retrofit;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealsRemoteSourceImpl implements MealsRemoteDataSource{
    private static final String URL = "www.themealdb.com/api/json/v1/1/";

    APIService apiService;
    private static MealsRemoteSourceImpl instance = null;

    private MealsRemoteSourceImpl(){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
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
    public void makeNetworkCall(NetworkCallBack networkCallBack) {
    }
}
