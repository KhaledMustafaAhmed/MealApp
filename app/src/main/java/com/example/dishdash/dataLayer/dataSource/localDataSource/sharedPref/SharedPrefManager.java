package com.example.dishdash.dataLayer.dataSource.localDataSource.sharedPref;

import com.f2prateek.rx.preferences2.Preference;

public class SharedPrefManager {
    private final SharedPreferenceLocalDataSource sharedPreferenceLocalDataSource;
    public SharedPrefManager(SharedPreferenceLocalDataSource sharedPreferenceLocalDataSource){
        this.sharedPreferenceLocalDataSource = sharedPreferenceLocalDataSource;
    }

    public void setUserId(String userId){
        sharedPreferenceLocalDataSource.setUserId(userId);
    }

    public void setMealId(String mealId){
        sharedPreferenceLocalDataSource.setMealId(mealId);
    }

    public void setDate(String date){
        sharedPreferenceLocalDataSource.setDate(date);
    }

    public Preference<String> getUser_ID(){
        return sharedPreferenceLocalDataSource.getUser_ID();
    }

    public Preference<String> getMEAL_ID(){
        return sharedPreferenceLocalDataSource.getMEAL_ID();
    }

    public Preference<String> getDATE_ID(){
        return sharedPreferenceLocalDataSource.getDATE_ID();
    }

}
