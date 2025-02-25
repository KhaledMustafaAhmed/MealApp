package com.example.dishdash.dataLayer.model.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(tableName = "PlannedMeal", primaryKeys = {"user_id","meal_id"})
public class PlannedMeal {
    @NonNull
    private String user_id;
    @NonNull
    private String meal_id;
    private String date;

    public PlannedMeal(String user_id, String meal_id, String date) {
        this.user_id = user_id;
        this.meal_id = meal_id;
        this.date = date;
    }

    @NonNull
    public String getUser_id() {
        return user_id;
    }

    @NonNull
    public String getMeal_id() {
        return meal_id;
    }

    public String getDate() {
        return date;
    }
}
