package com.example.dishdash.uiLayer.home.interfaces;

import com.example.dishdash.dataLayer.model.pojo.areaCustomPojo.CountryItem;
import com.example.dishdash.dataLayer.model.pojo.categoryCustomPojo.CategoryItem;
import com.example.dishdash.dataLayer.model.pojo.mealsList.MeaList;
import com.example.dishdash.dataLayer.model.pojo.popularCustomPojo.PopularItem;

import java.util.List;

public interface IHomeView {
    public void receiveRandoMeal(MeaList meaList);

    public void receiveMealByID(MeaList meaList);

    public void receivePopularItems(List<PopularItem> popularList);

    public void receiveAllCategoriesItems(List<CategoryItem> categoryList);

    public void receiveAllCountriesItems(List<CountryItem> areaList);

    public void doLogout();

    public void logoutCheckUserResponse(String user);
}
