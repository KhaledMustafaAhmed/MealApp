package com.example.dishdash.uiLayer.splash;

import com.example.dishdash.dataLayer.dataSource.localDataSource.sharedPref.SharedPrefManager;
import com.example.dishdash.dataLayer.dataSource.localDataSource.sharedPref.SharedPreferenceLocalDataSource;
import com.example.dishdash.dataLayer.repository.userRepo.FirebaseRepository;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SplashPresenter implements SplashContract{
    FirebaseRepository firebaseRepository;
    SharedPrefManager sharedPrefManager;
    ISplashView iSplashView;

    public SplashPresenter(ISplashView iSplashView,FirebaseRepository firebaseRepository, SharedPrefManager sharedPrefManager){
        this.firebaseRepository = firebaseRepository;
        this.sharedPrefManager = sharedPrefManager;
        this.iSplashView = iSplashView;
    }
    @Override
    public FirebaseUser getCurrentUser() {
        return firebaseRepository.getCurrentUser();
    }

    @Override
    public void checkUser() {
         sharedPrefManager.getUser_ID().asObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(USER_ID -> {
                    if(USER_ID == null || USER_ID.isEmpty()){
                        iSplashView.userNotFound();
                    }else{
                        iSplashView.userFound();
                    }
                });
    }
}
