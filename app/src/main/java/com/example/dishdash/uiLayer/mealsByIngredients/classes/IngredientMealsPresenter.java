package com.example.dishdash.uiLayer.mealsByIngredients.classes;

import com.example.dishdash.dataLayer.model.pojo.ingredientsCustomPojo.IngredientList;
import com.example.dishdash.dataLayer.model.pojo.popularCustomPojo.PopularList;
import com.example.dishdash.dataLayer.repository.mealsRepo.MealsRepository;
import com.example.dishdash.uiLayer.mealsByCategory.interfaces.ICategoryMealsView;
import com.example.dishdash.uiLayer.mealsByIngredients.interfaces.IngredientMealsContract;
import com.example.dishdash.uiLayer.mealsByIngredients.interfaces.IngredientMealsView;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class IngredientMealsPresenter implements IngredientMealsContract {
    private final MealsRepository mealsRepository;
    private final IngredientMealsView ingredientMealsView;

    public IngredientMealsPresenter(MealsRepository mealsRepository, IngredientMealsView ingredientMealsView) {
        this.mealsRepository = mealsRepository;
        this.ingredientMealsView = ingredientMealsView;
    }

    @Override
    public void getIngredientMeals(String name) {
        mealsRepository.getMealsBasedOnIngredient(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<PopularList>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(PopularList popularList) {
                        ingredientMealsView.receiveIngredientMeals(popularList.getPopularItemList());
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });
    }
}
