package com.example.dishdash.uiLayer.search.interfaces;

public interface SearchContract {
    public void getAllIngredients(String ingredientCode);
    public void getAllCategories(String categoryCode);
    public void getAllCountries(String countryCode);
    public void searchMeals(String query);
}
