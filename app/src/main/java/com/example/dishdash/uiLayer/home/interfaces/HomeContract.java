package com.example.dishdash.uiLayer.home.interfaces;

public interface HomeContract {
    public void getRandoMeal();

    public void getMealByID(String meal_id);

    public void getMealsBasedOnCategory(String category);

    public void getAllCategories(String categoryListCode);

    public void getAllCountries(String countryListCode);

    public void logout();

    public void checkMealOfTheDay();

    public void saveMealOfDay(String meal_id);

    public String checkUserMode();
}
