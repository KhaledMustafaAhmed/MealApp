package com.example.dishdash.uiLayer.mealsByCountry.classes;

import com.example.dishdash.dataLayer.model.pojo.popularCustomPojo.PopularList;
import com.example.dishdash.dataLayer.repository.mealsRepo.MealsRepository;
import com.example.dishdash.uiLayer.mealsByCategory.interfaces.CategoryMealsContract;
import com.example.dishdash.uiLayer.mealsByCategory.interfaces.ICategoryMealsView;
import com.example.dishdash.uiLayer.mealsByCountry.interfaces.CountryMealsContract;
import com.example.dishdash.uiLayer.mealsByCountry.interfaces.ICountryMealsViews;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CountryMealsPresenter implements CountryMealsContract {
    private final MealsRepository mealsRepository;
    private final ICountryMealsViews iCountryMealsViews;
    public CountryMealsPresenter(MealsRepository mealsRepository,ICountryMealsViews iCountryMealsViews ){
        this.iCountryMealsViews = iCountryMealsViews;
        this.mealsRepository = mealsRepository;
    }
    @Override
    public void getMealsBasedOnCountries(String countryName) {
        mealsRepository.getMealsBasedOnCountry(countryName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<PopularList>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(PopularList popularList) {
                        iCountryMealsViews.receiveCountryMeals(popularList.getPopularItemList());
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });
    }
}
