package com.example.dishdash.dataLayer.dataSource.localDataSource.sharedPref;

import android.util.Log;

public class SharedPrefManager {
    private final SharedPreferenceLocalDataSource sharedPreferenceLocalDataSource;

    public SharedPrefManager(SharedPreferenceLocalDataSource sharedPreferenceLocalDataSource) {
        this.sharedPreferenceLocalDataSource = sharedPreferenceLocalDataSource;
    }

    public void setUserId(String userId) {
        Log.d("TAG", "setUserId: in shared manager " + userId);
        sharedPreferenceLocalDataSource.setUserId(userId);
    }

    public void setMealId(String mealId) {
        sharedPreferenceLocalDataSource.setMealId(mealId);
    }

    public void setDate(String date) {
        sharedPreferenceLocalDataSource.setDate(date);
    }

    public String getUserId() {
        return sharedPreferenceLocalDataSource.getUserId();
    }

    public String getMealId() {
        return sharedPreferenceLocalDataSource.getMealId();
    }

    public String getDate() {
        return sharedPreferenceLocalDataSource.getDate();
    }

    public void setLoggedIn(boolean loggedIn) {
        sharedPreferenceLocalDataSource.setLoggedIn(loggedIn);
    }

    public boolean isLoggedIn() {
        return sharedPreferenceLocalDataSource.isLoggedIn();
    }
}