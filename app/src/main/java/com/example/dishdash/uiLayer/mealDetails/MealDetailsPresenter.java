package com.example.dishdash.uiLayer.mealDetails;

import android.util.Log;

import com.example.dishdash.dataLayer.model.pojo.mealsList.MeaList;
import com.example.dishdash.dataLayer.repository.mealsRepo.MealsRepository;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MealDetailsPresenter implements MealDetailsContract {
    private static final String TAG = "MealDetailsPresenter";
    MealsRepository mealsRepository;
    IMealDetailsView iMealDetailsView;
    public MealDetailsPresenter(IMealDetailsView iMealDetailsView, MealsRepository mealsRepository){
        this.iMealDetailsView = iMealDetailsView;
        this.mealsRepository = mealsRepository;
    }
    @Override
    public void getMeal(String mealID) {
        mealsRepository.getMealByID(mealID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MeaList>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onSuccess(MeaList meaList) {
                        Log.i(TAG, "onSuccess: ");
                        iMealDetailsView.getMealDetails(meaList.getMeals().get(0));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: "+e.getLocalizedMessage());
                    }
                });
    }
}
