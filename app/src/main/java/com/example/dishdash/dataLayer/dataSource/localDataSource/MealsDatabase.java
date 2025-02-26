package com.example.dishdash.dataLayer.dataSource.localDataSource;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.dishdash.dataLayer.model.entities.FavouriteMeal;
import com.example.dishdash.dataLayer.model.entities.MealConverter;
import com.example.dishdash.dataLayer.model.entities.PlannedMeal;

@Database(entities = {FavouriteMeal.class, PlannedMeal.class}, version = 2)
@TypeConverters(MealConverter.class)
public abstract class MealsDatabase extends RoomDatabase {
    public abstract FavouritesDAO FavouritesDAO();
    public abstract PlannedDAO PlannedDAO();
    private static MealsDatabase instance = null;

    public static MealsDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), MealsDatabase.class, "Mealsdb").build();
        }
        return instance;
    }
}
