package com.example.dishdash.dataLayer.dataSource.localDataSource.sharedPref;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.f2prateek.rx.preferences2.Preference;
import com.f2prateek.rx.preferences2.RxSharedPreferences;

public class SharedPreferenceLocalDataSource {
    private RxSharedPreferences rxSharedPreferences;
    private Preference<String> USER_ID;
    private Preference<String> MEAL_ID;
    private Preference<String> DATE_ID;

    private static SharedPreferenceLocalDataSource instance = null;
    private SharedPreferenceLocalDataSource(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        rxSharedPreferences = RxSharedPreferences.create(sharedPreferences);
        USER_ID = rxSharedPreferences.getString("USER_ID");
        MEAL_ID = rxSharedPreferences.getString("MEAL_ID");
        DATE_ID = rxSharedPreferences.getString("DATE_ID");
    }

    public static SharedPreferenceLocalDataSource getInstance(Context context) {
        if(instance == null){
            instance = new SharedPreferenceLocalDataSource(context);
        }
        return instance;
    }

    public void setUserId(String userId){
        USER_ID.set(userId);
    }

    public void setMealId(String mealId){
        MEAL_ID.set(mealId);
    }

    public void setDate(String date){
        DATE_ID.set(date);
    }

    public Preference<String> getUser_ID(){
        return USER_ID;
    }

    public Preference<String> getMEAL_ID(){
        return MEAL_ID;
    }

    public Preference<String> getDATE_ID(){
        return DATE_ID;
    }

}
