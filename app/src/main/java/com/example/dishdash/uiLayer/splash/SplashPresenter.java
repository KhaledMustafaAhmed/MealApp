package com.example.dishdash.uiLayer.splash;

import android.util.Log;

import com.example.dishdash.dataLayer.dataSource.localDataSource.sharedPref.SharedPrefManager;
import com.example.dishdash.dataLayer.dataSource.localDataSource.sharedPref.SharedPreferenceLocalDataSource;
import com.example.dishdash.dataLayer.repository.userRepo.FirebaseRepository;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SplashPresenter implements SplashContract{
    private final SharedPrefManager sharedPrefManager;
    public SplashPresenter( SharedPrefManager sharedPrefManager){
        this.sharedPrefManager = sharedPrefManager;
    }
    @Override
    public String checkUserMode() {
       return sharedPrefManager.getUserId();
    }
}
