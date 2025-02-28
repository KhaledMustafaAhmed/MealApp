package com.example.dishdash.uiLayer.plans;

import com.example.dishdash.dataLayer.dataSource.localDataSource.sharedPref.SharedPrefManager;
import com.example.dishdash.dataLayer.model.entities.PlannedMeal;
import com.example.dishdash.dataLayer.model.pojo.mealsList.MeaList;
import com.example.dishdash.dataLayer.repository.mealsRepo.MealsRepository;
import com.example.dishdash.dataLayer.repository.userRepo.FirebaseRepository;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PlansPresenter implements IPlansContract{

    private MealsRepository mealsRepository;
    private FirebaseRepository firebaseRepository;
    private IPlansViews iPlansViews;
    private SharedPrefManager sharedPrefManager;

    public PlansPresenter(MealsRepository mealsRepository, FirebaseRepository firebaseRepository, IPlansViews iPlansViews, SharedPrefManager sharedPrefManager) {
        this.mealsRepository = mealsRepository;
        this.firebaseRepository = firebaseRepository;
        this.iPlansViews = iPlansViews;
        this.sharedPrefManager =sharedPrefManager;
    }

    @Override
    public void gelPlannedMeal(String meal_id, String date) {
        mealsRepository.deletePlannedMeal(firebaseRepository.getUserID(), meal_id,date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        iPlansViews.showPlannedDeleted();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    @Override
    public void getAllPlannedMealsForUser() {
        mealsRepository.getPlannedMealForUser(firebaseRepository.getUserID())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<PlannedMeal>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<PlannedMeal> plannedMeals) {
                        iPlansViews.receiveAllPlannedMeals(plannedMeals);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    @Override
    public void checkUserMode() {
        String mode =  sharedPrefManager.getUserId();
        if(mode.equals("GUEST")){
            iPlansViews.showAnimation();
        }else{
            iPlansViews.getUserData();
        }
    }
}
