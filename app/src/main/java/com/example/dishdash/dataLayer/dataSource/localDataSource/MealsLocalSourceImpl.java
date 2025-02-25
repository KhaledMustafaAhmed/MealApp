package com.example.dishdash.dataLayer.dataSource.localDataSource;

import android.content.Context;

import com.example.dishdash.dataLayer.model.entities.FavouriteMeal;
import com.example.dishdash.dataLayer.model.entities.PlannedMeal;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public class MealsLocalSourceImpl implements IMealsLocalSource {
    private FavouritesDAO favouritesDAO;
    private PlannedDAO plannedDAO;

    private  static MealsLocalSourceImpl instance = null;
    private MealsLocalSourceImpl(Context context) {
        MealsDatabase mealsDatabase = MealsDatabase.getInstance(context.getApplicationContext());
        plannedDAO =  mealsDatabase.PlannedDAO();
        favouritesDAO = mealsDatabase.FavouritesDAO();
    }

    public static MealsLocalSourceImpl getInstance(Context context){
        if(instance == null){
            instance = new MealsLocalSourceImpl(context);
        }
        return instance;
    }

    @Override
    public Completable addFavMeal(FavouriteMeal favouriteMeals) {
        return favouritesDAO.insertFavMeal(favouriteMeals);
    }

    @Override
    public Completable deleteFavMeal(String user_id, String meal_id) {
        return favouritesDAO.deleteFavMeal(user_id, meal_id);
    }

    @Override
    public Single<List<FavouriteMeal>> getFavouriteMealsForUser(String user_id) {
        return favouritesDAO.getFavouriteMealsForUser(user_id);
    }

    @Override
    public Completable addPlannedMeal(PlannedMeal plannedMeal) {
        return plannedDAO.insertPlannedMeal(plannedMeal);
    }

    @Override
    public Completable deletePlannedMeal(String user_id, String meal_id) {
        return plannedDAO.deletePlannedMeal(user_id, meal_id);
    }

    @Override
    public Single<List<PlannedMeal>> getPlannedMealForUser(String user_id) {
        return plannedDAO.getPlannedMealForUser(user_id);
    }
}
