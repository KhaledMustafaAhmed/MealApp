package com.example.dishdash.uiLayer.mealDetails;

import android.util.Log;

import com.example.dishdash.dataLayer.model.entities.FavouriteMeal;
import com.example.dishdash.dataLayer.model.entities.PlannedMeal;
import com.example.dishdash.dataLayer.model.pojo.mealsList.MeaList;
import com.example.dishdash.dataLayer.model.pojo.mealsList.MealsItem;
import com.example.dishdash.dataLayer.repository.mealsRepo.MealsRepository;
import com.example.dishdash.dataLayer.repository.userRepo.FirebaseRepository;

import java.util.Locale;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MealDetailsPresenter implements MealDetailsContract {
    private static final String TAG = "MealDetailsPresenter";
    MealsRepository mealsRepository;
    IMealDetailsView iMealDetailsView;

    FirebaseRepository firebaseRepository;

    public MealDetailsPresenter(IMealDetailsView iMealDetailsView, MealsRepository mealsRepository, FirebaseRepository firebaseRepository){
        this.iMealDetailsView = iMealDetailsView;
        this.mealsRepository = mealsRepository;
        this.firebaseRepository =firebaseRepository;
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

    @Override
    public void addMealToFavourites(String user_id, MealsItem mealsItem) {
        addFavouriteMealToRemote(user_id, mealsItem);

        mealsRepository.addFavMeal(new FavouriteMeal(mealsItem.getIdMeal(),user_id,mealsItem))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: in data base");
                        iMealDetailsView.showInsetFavSuccess();
                    }

                    @Override
                    public void onError(Throwable e) {
                        iMealDetailsView.showInsetFavFailed();
                    }
                });
    }

    @Override
    public void addFavouriteMealToRemote(String user_id, MealsItem mealsItem) {
        firebaseRepository.addFavouriteMeal(user_id, new FavouriteMeal(user_id, mealsItem.getIdMeal(), mealsItem));
    }

    @Override
    public String getUserID() {
        return firebaseRepository.getUserID();
    }

    @Override
    public String calcDate(int year, int monthOfYear, int dayOfMonth) {
        int month = monthOfYear + 1;
        String strMonth = ""+month;
        String strDayOfMonth = ""+dayOfMonth;

        if(month < 10){
            strMonth = "0"+ strMonth;
        }
        if(dayOfMonth<10){
            strDayOfMonth = "0"+ strDayOfMonth;
        }

        return   year+"-"+ strMonth+ "-"+strDayOfMonth;
    }

    @Override
    public void addMealToWeeklyPlan(MealsItem mealsItem, String date) {
        mealsRepository.addPlannedMeal(new PlannedMeal(firebaseRepository.getUserID(), mealsItem.getIdMeal(), date, mealsItem))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        iMealDetailsView.showAddedMealPlanSuccess();
                    }

                    @Override
                    public void onError(Throwable e) {
                        iMealDetailsView.showAddedMealPlanFailed();
                    }
                });
    }
}
