package com.example.dishdash.uiLayer.favourites;

import android.util.Log;

import com.example.dishdash.dataLayer.dataSource.localDataSource.sharedPref.SharedPrefManager;
import com.example.dishdash.dataLayer.model.entities.FavouriteMeal;
import com.example.dishdash.dataLayer.repository.mealsRepo.MealsRepository;
import com.example.dishdash.dataLayer.repository.userRepo.FirebaseRepository;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FavouritesMealsPresenter implements IFavouritesContract{

    private MealsRepository mealsRepository;
    private FirebaseRepository firebaseRepository;
    private IFavouritesView iFavouritesView;

    private SharedPrefManager sharedPrefManager;
    public FavouritesMealsPresenter(IFavouritesView iFavouritesView,MealsRepository mealsRepository, FirebaseRepository firebaseRepository, SharedPrefManager sharedPrefManager){
        this.mealsRepository = mealsRepository;
        this.firebaseRepository = firebaseRepository;
        this.iFavouritesView = iFavouritesView;
        this.sharedPrefManager = sharedPrefManager;
    }
    @Override
    public void getFavouritesItems() {
        mealsRepository.getFavouriteMealsForUser(firebaseRepository.getUserID())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<FavouriteMeal>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<FavouriteMeal> favouriteMeals) {
                        iFavouritesView.receiveFavouritesItem(favouriteMeals);
                        Log.d("TAG", "onSuccess: in fav presenter");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("TAG", "onError: in fav presenter");
                    }
                });
    }

    @Override
    public void deleteFavouriteItem(String meal_id) {
        mealsRepository.deleteFavMeal(firebaseRepository.getUserID(), meal_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d("TAG", "onComplete: in delete presenter");
                        iFavouritesView.deletionSuccess();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    @Override
    public void checkUserMode() {
        String mode = sharedPrefManager.getUserId();
        if(mode.equals("GUEST")){
            iFavouritesView.showAnimation();
        }else{
            iFavouritesView.getUserData();
        }
    }


}
