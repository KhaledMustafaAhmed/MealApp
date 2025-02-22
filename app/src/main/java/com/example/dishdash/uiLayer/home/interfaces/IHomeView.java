package com.example.dishdash.uiLayer.home.interfaces;

import com.example.dishdash.dataLayer.model.pojo.mealsList.MeaList;
import com.example.dishdash.dataLayer.model.pojo.popularCustomPojo.PopularItem;
import com.example.dishdash.dataLayer.model.pojo.popularCustomPojo.PopularList;

import java.util.List;

public interface IHomeView {
    public void receiveRandoMeal(MeaList meaList);

    public void receivePopularItems(List<PopularItem> popularList);

    public void doLogout();
}
