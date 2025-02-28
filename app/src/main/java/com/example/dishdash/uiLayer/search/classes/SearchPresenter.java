package com.example.dishdash.uiLayer.search.classes;

import android.util.Log;

import com.example.dishdash.dataLayer.model.pojo.areaCustomPojo.CountryItem;
import com.example.dishdash.dataLayer.model.pojo.areaCustomPojo.CountryList;
import com.example.dishdash.dataLayer.model.pojo.categoryCustomPojo.CategoryItem;
import com.example.dishdash.dataLayer.model.pojo.categoryCustomPojo.CategoryList;
import com.example.dishdash.dataLayer.model.pojo.ingredientsCustomPojo.IngredientItem;
import com.example.dishdash.dataLayer.model.pojo.ingredientsCustomPojo.IngredientList;
import com.example.dishdash.dataLayer.repository.mealsRepo.MealsRepository;
import com.example.dishdash.uiLayer.search.interfaces.ISearchVew;
import com.example.dishdash.uiLayer.search.interfaces.SearchContract;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchPresenter implements SearchContract {
    private MealsRepository mealsRepository;
    private ISearchVew iSearchVew;
    private List<IngredientItem> allIngredients = new ArrayList<>();
    private List<CategoryItem> allCategories = new ArrayList<>();
    private List<CountryItem> allCountries = new ArrayList<>();


    public SearchPresenter(MealsRepository mealsRepository, ISearchVew iSearchVew) {
        this.mealsRepository = mealsRepository;
        this.iSearchVew = iSearchVew;
    }


    @Override
    public void getAllIngredients(String ingredientCode) {
        mealsRepository.getAllIngredients(ingredientCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<IngredientList>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("TAG", "onSubscribe: getAllIngredients");
                    }

                    @Override
                    public void onSuccess(IngredientList ingredientList) {
                        Log.d("TAG", "onSuccess: getAllIngredients");
                        allIngredients = ingredientList.getMeals();
                        iSearchVew.receiveIngredientList(ingredientList.getMeals());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("TAG", "onError: getAllIngredients");
                    }
                });
    }

    @Override
    public void getAllCategories(String categoryCode) {
        mealsRepository.getAllCategories(categoryCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<CategoryList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(CategoryList categoryList) {
                        allCategories = categoryList.getMeals();
                        iSearchVew.receiveCategoriesList(categoryList.getMeals());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    @Override
    public void getAllCountries(String countryCode) {
        mealsRepository.getAllCountries(countryCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<CountryList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(CountryList countryList) {
                        allCountries = countryList.getMeals();
                        iSearchVew.receiveCountryList(countryList.getMeals());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    @Override
    public void searchMeals(String query) {
        List<IngredientItem> filteredIngredients = filterIngredients(query);
        iSearchVew.receiveIngredientList(filteredIngredients);

        List<CategoryItem> filteredCategories = filterCategories(query);
        iSearchVew.receiveCategoriesList(filteredCategories);

        List<CountryItem> filteredCountries = filterCountries(query);
        iSearchVew.receiveCountryList(filteredCountries);
    }

    private List<IngredientItem> filterIngredients(String query) {
        List<IngredientItem> filteredList = new ArrayList<>();
        for (IngredientItem item : allIngredients) {
            if (item.getStrIngredient().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(item);
            }
        }
        return filteredList.isEmpty()? allIngredients: filteredList;
    }

    private List<CategoryItem> filterCategories(String query) {
        List<CategoryItem> filteredList = new ArrayList<>();
        for (CategoryItem item : allCategories) {
            if (item.getStrCategory().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(item);
            }
        }
        return filteredList.isEmpty()? allCategories: filteredList;
    }

    private List<CountryItem> filterCountries(String query) {
        List<CountryItem> filteredList = new ArrayList<>();
        for (CountryItem item : allCountries) {
            if (item.getStrArea().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(item);
            }
        }
        return filteredList.isEmpty()? allCountries: filteredList;
    }
}
