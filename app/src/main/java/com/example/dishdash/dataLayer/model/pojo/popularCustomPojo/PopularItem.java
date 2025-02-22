package com.example.dishdash.dataLayer.model.pojo.popularCustomPojo;

import com.google.gson.annotations.SerializedName;

public class PopularItem {
    @SerializedName("strMeal")
    public String strMeal;
    @SerializedName("strMealThumb")
    public String strMealThumb;
    @SerializedName("idMeal")
    public String idMeal;

    public String getStrMeal() {
        return strMeal;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public String getIdMeal() {
        return idMeal;
    }

}
