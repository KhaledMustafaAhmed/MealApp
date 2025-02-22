package com.example.dishdash.uiLayer.home.classes;

import android.util.Log;

import com.example.dishdash.dataLayer.model.pojo.mealsList.MeaList;
import com.example.dishdash.dataLayer.model.pojo.popularCustomPojo.PopularList;
import com.example.dishdash.dataLayer.repository.mealsRepo.MealsRepository;
import com.example.dishdash.uiLayer.home.interfaces.HomeContract;
import com.example.dishdash.uiLayer.home.interfaces.IHomeView;

import io.reactivex.Observer;
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
                .subscribeOn(Schedulers.io())
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

    @Override
    public void getPopularItems(String category) {
        mealsRepository.getPopularItems(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PopularList>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe: in getPopularItems home presenter " );
                    }

                    @Override
                    public void onNext(PopularList popularList) {
                        Log.e(TAG, "onNext: in getPopularItems home presenter " );
                        iHomeView.receivePopularItems(popularList.getPopularItemList());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: in getPopularItems home presenter " );
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: in getPopularItems home presenter " );
                    }
                });
    }

    @Override
    public void logout() {
        
    }
}
