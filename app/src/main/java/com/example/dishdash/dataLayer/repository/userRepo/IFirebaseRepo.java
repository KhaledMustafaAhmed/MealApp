package com.example.dishdash.dataLayer.repository.userRepo;

import com.example.dishdash.dataLayer.model.User;
import com.example.dishdash.dataLayer.model.entities.FavouriteMeal;
import com.example.dishdash.dataLayer.model.entities.PlannedMeal;
import com.google.firebase.auth.FirebaseUser;

public interface IFirebaseRepo {
    public void loginWithFirebase(User user, FirebaseCallback callback);
    public void signupWithFirebase(User user, FirebaseCallback callback);
    public void logout();

    public FirebaseUser getCurrentUser();

    public String getUserID();

    public void addFavouriteMeal(String userId, FavouriteMeal favouriteMeal);

    public void addPlannedMeal(String userId, PlannedMeal plannedMeal);

    }
