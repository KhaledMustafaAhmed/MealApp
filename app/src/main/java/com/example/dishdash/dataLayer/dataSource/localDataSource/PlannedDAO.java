package com.example.dishdash.dataLayer.dataSource.localDataSource;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.dishdash.dataLayer.model.entities.PlannedMeal;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface PlannedDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insertPlannedMeal(PlannedMeal plannedMeal);

    @Query("DELETE FROM PlannedMeal WHERE user_id = :user_id and meal_id = :meal_id and date = :date")
    Completable deletePlannedMeal(String user_id, String meal_id, String date);

    @Query("SELECT * FROM PlannedMeal WHERE user_id = :user_id ORDER BY date ASC")
    Single<List<PlannedMeal>> getPlannedMealForUser(String user_id);
}
