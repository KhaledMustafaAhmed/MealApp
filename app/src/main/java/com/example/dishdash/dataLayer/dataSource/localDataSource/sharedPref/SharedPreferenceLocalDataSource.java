package com.example.dishdash.dataLayer.dataSource.localDataSource.sharedPref;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class SharedPreferenceLocalDataSource {
    private final SharedPreferences sharedPreferences;

    private static SharedPreferenceLocalDataSource instance = null;

    private SharedPreferenceLocalDataSource(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static SharedPreferenceLocalDataSource getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPreferenceLocalDataSource(context);
        }
        return instance;
    }

    public void setUserId(String userId) {
        Log.d("TAG", "setUserId: in shared local impl " + userId);
        sharedPreferences.edit().putString("USER_ID", userId).apply();
    }

    public void setMealId(String mealId) {
        sharedPreferences.edit().putString("MEAL_ID", mealId).apply();
    }

    public void setDate(String date) {
        sharedPreferences.edit().putString("DATE_ID", date).apply();
    }

    public String getUserId() {
        return sharedPreferences.getString("USER_ID", null);
    }

    public String getMealId() {
        return sharedPreferences.getString("MEAL_ID", null);
    }

    public String getDate() {
        return sharedPreferences.getString("DATE_ID", null);
    }

    public void setLoggedIn(boolean loggedIn) {
        sharedPreferences.edit().putBoolean("IS_LOGGED_IN", loggedIn).apply();
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean("IS_LOGGED_IN", false);
    }
}