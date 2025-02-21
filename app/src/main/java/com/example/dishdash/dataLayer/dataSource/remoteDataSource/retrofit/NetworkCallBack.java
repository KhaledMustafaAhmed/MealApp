package com.example.dishdash.dataLayer.dataSource.remoteDataSource.retrofit;

import java.util.List;

public interface NetworkCallBack<T> {
    public void onSuccessful(T value);

    public void onFailure(String errorMessage);}
