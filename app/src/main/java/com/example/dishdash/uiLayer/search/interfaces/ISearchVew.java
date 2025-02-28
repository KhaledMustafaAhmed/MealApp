package com.example.dishdash.uiLayer.search.interfaces;

import com.example.dishdash.dataLayer.model.pojo.areaCustomPojo.CountryItem;
import com.example.dishdash.dataLayer.model.pojo.categoryCustomPojo.CategoryItem;
import com.example.dishdash.dataLayer.model.pojo.ingredientsCustomPojo.IngredientItem;

import java.util.List;

public interface ISearchVew {
    public void receiveIngredientList(List<IngredientItem> ingredientList);

    public void receiveCategoriesList(List<CategoryItem> categoryList);

    public void receiveCountryList(List<CountryItem> countryList);
}
