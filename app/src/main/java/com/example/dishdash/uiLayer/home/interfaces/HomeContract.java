package com.example.dishdash.uiLayer.home.interfaces;

public interface HomeContract {
    public void getRandoMeal();

    public void getMealsBasedOnCategory(String category);

    public void getAllCategories(String categoryListCode);

    public void getAllCountries(String countryListCode);

    public void logout();
}
