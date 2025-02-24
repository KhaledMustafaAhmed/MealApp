package com.example.dishdash.uiLayer.home.classes;

import android.util.Log;

import com.example.dishdash.dataLayer.model.pojo.areaCustomPojo.CountryList;
import com.example.dishdash.dataLayer.model.pojo.categoryCustomPojo.CategoryList;
import com.example.dishdash.dataLayer.model.pojo.mealsList.MeaList;
import com.example.dishdash.dataLayer.model.pojo.popularCustomPojo.PopularList;
import com.example.dishdash.dataLayer.repository.mealsRepo.MealsRepository;
import com.example.dishdash.dataLayer.repository.userRepo.FirebaseRepository;
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
    FirebaseRepository firebaseRepository;
    public HomePresenter(IHomeView iHomeView,MealsRepository mealsRepository, FirebaseRepository firebaseRepository){
        this.iHomeView = iHomeView;
        this.mealsRepository = mealsRepository;
        this.firebaseRepository = firebaseRepository;
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
    public void getMealsBasedOnCategory(String category) {
        mealsRepository.getMealsBasedOnCategory(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<PopularList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(PopularList popularList) {
                        iHomeView.receivePopularItems(popularList.getPopularItemList());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    @Override
    public void getAllCategories(String categoryListCode) {
        mealsRepository.getAllCategories(categoryListCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        categoryList -> {
                            iHomeView.receiveAllCategoriesItems(categoryList.getMeals());
                            Log.d(TAG, "getAllCategories: success method");
                        },
                        error -> Log.d(TAG, "getAllCategories: error method")
                );
    }

    @Override
    public void getAllCountries(String countryListCode) {
        mealsRepository.getAllCountries(countryListCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<CountryList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(CountryList countryList) {
                        Log.d(TAG, "onSuccess: in country");
                         iHomeView.receiveAllCountriesItems(countryList.getMeals());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: "+e.getLocalizedMessage() );
                    }
                });
    }

    @Override
    public void logout() {
        firebaseRepository.logout();
        iHomeView.doLogout();
    }
}
