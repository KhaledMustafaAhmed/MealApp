package com.example.dishdash.dataLayer.model.pojo.popularCustomPojo;

import java.util.List;

public class PopularList {
    private List<PopularItem> meals;

    public List<PopularItem> getPopularItemList() {
        return meals;
    }

    public void setMeals(List<PopularItem> meals) {
        this.meals = meals;
    }

    public int getSize(){
        return  meals.size();
    }
}
