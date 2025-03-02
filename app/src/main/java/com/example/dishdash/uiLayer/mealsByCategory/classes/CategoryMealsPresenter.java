package com.example.dishdash.uiLayer.mealsByCategory.classes;

import com.example.dishdash.dataLayer.model.pojo.popularCustomPojo.PopularList;
import com.example.dishdash.dataLayer.repository.mealsRepo.MealsRepository;
import com.example.dishdash.uiLayer.mealsByCategory.interfaces.CategoryMealsContract;
import com.example.dishdash.uiLayer.mealsByCategory.interfaces.ICategoryMealsView;

import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CategoryMealsPresenter implements CategoryMealsContract {
    private final MealsRepository mealsRepository;
    private final ICategoryMealsView iCategoryMealsView;

    public CategoryMealsPresenter(ICategoryMealsView iCategoryMealsView, MealsRepository mealsRepository){
        this.iCategoryMealsView = iCategoryMealsView;
        this.mealsRepository = mealsRepository;
    }
    @Override
    public void getCategoryMeals(String categoryName) {
        mealsRepository.getMealsBasedOnCategory(categoryName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<PopularList>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(PopularList popularList) {
                        iCategoryMealsView.receiveCategoryMeals(popularList.getPopularItemList());
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });
    }
}
