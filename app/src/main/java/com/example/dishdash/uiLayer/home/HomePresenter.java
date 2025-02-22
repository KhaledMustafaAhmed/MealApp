package com.example.dishdash.uiLayer.home;

import android.util.Log;

import com.example.dishdash.dataLayer.model.pojo.MeaList;
import com.example.dishdash.dataLayer.repository.mealsRepo.MealsRepository;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter implements HomeContract {
    private static final String TAG = "HomePresenter";
    IHomeView iHomeView;
    MealsRepository mealsRepository;
    public HomePresenter(IHomeView iHomeView,MealsRepository mealsRepository){
        this.iHomeView = iHomeView;
        this.mealsRepository = mealsRepository;
    }

    @Override
    public void getRandoMeal() {
        mealsRepository.getRandoMeal()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MeaList>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe: in Home Presenter" );
                    }

                    @Override
                    public void onSuccess(MeaList meaList) {
                        iHomeView.receiveRandoMeal(meaList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: in Home Presenter" );
                    }
                });
    }
}
