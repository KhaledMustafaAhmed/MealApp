package com.example.dishdash.uiLayer.home.classes;

import static io.reactivex.Single.zip;

import android.annotation.SuppressLint;
import android.util.Log;
import android.util.Pair;

import com.example.dishdash.dataLayer.dataSource.localDataSource.sharedPref.SharedPrefManager;
import com.example.dishdash.dataLayer.dataSource.remoteDataSource.userRemoteDataSource.GoogleAuth;
import com.example.dishdash.dataLayer.model.pojo.areaCustomPojo.CountryList;
import com.example.dishdash.dataLayer.model.pojo.categoryCustomPojo.CategoryList;
import com.example.dishdash.dataLayer.model.pojo.mealsList.MeaList;
import com.example.dishdash.dataLayer.model.pojo.popularCustomPojo.PopularList;
import com.example.dishdash.dataLayer.repository.mealsRepo.MealsRepository;
import com.example.dishdash.dataLayer.repository.userRepo.FirebaseRepository;
import com.example.dishdash.uiLayer.home.interfaces.HomeContract;
import com.example.dishdash.uiLayer.home.interfaces.IHomeView;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter implements HomeContract {
    private static final String TAG = "HomePresenter";
    private IHomeView iHomeView;
    private MealsRepository mealsRepository;
    private FirebaseRepository firebaseRepository;
    private SharedPrefManager sharedPrefManager;
   // private GoogleAuth googleAuth;
    public HomePresenter(IHomeView iHomeView,MealsRepository mealsRepository, FirebaseRepository firebaseRepository, SharedPrefManager sharedPrefManager){
        this.iHomeView = iHomeView;
        this.mealsRepository = mealsRepository;
        this.firebaseRepository = firebaseRepository;
        this.sharedPrefManager = sharedPrefManager;
       // this.googleAuth  =googleAuth;
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
    public void getMealByID(String meal_id) {
        mealsRepository.getMealByID(meal_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meaList -> {
                   iHomeView.receiveMealByID(meaList);
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

        sharedPrefManager.setUserId("GUEST");
        iHomeView.doLogout();
    }

    @Override
    public void checkMealOfTheDay() {
        String mealId = sharedPrefManager.getMealId();
        String dateId = sharedPrefManager.getDate();

        if (dateId != null && dateId.equals(getCurrentDate())) {
            getMealByID(mealId);
            Log.d(TAG, "checkMealOfTheDay: yes");
        } else {
            Log.d(TAG, "checkMealOfTheDay: no");
            getRandoMeal();
        }
    }

    @Override
    public void saveMealOfDay(String meal_id) {
        sharedPrefManager.setMealId(meal_id);
        sharedPrefManager.setDate(getCurrentDate());
        Log.d(TAG, "saveMealOfDay: ");
    }

    @Override
    public String checkUserMode() {
        return sharedPrefManager.getUserId();
    }

    private String getCurrentDate(){
        SimpleDateFormat sdf =new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(new Date());
    }
}
