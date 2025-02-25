package com.example.dishdash.dataLayer.dataSource.localDataSource;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.dishdash.dataLayer.model.entities.FavouriteMeal;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface FavouritesDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insertFavMeal(FavouriteMeal favouriteMeals);

    @Query("DELETE FROM FavouriteMeals WHERE user_id = :user_id and meal_id = :meal_id")
    Completable deleteFavMeal(String user_id, String meal_id );

    @Query("SELECT * FROM FavouriteMeals WHERE user_id = :user_id")
    Single<List<FavouriteMeal>> getFavouriteMealsForUser(String user_id);
}
