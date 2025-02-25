package com.example.dishdash.dataLayer.repository.userRepo;

import com.example.dishdash.dataLayer.dataSource.remoteDataSource.userRemoteDataSource.FirebaseRemoteDataSource;
import com.example.dishdash.dataLayer.model.User;
import com.example.dishdash.dataLayer.model.entities.FavouriteMeal;
import com.example.dishdash.dataLayer.model.entities.PlannedMeal;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseRepository implements IFirebaseRepo {
    private final FirebaseRemoteDataSource firebaseRemoteDataSource;

    public FirebaseRepository(FirebaseRemoteDataSource _firebaseRemoteDataSource){
        firebaseRemoteDataSource = _firebaseRemoteDataSource;
    }

    @Override
    public void loginWithFirebase(User user, FirebaseCallback callback) {
        firebaseRemoteDataSource.login(user, callback);
    }

    @Override
    public void signupWithFirebase(User user, FirebaseCallback callback) {
        firebaseRemoteDataSource.signup(user, callback);
    }

    @Override
    public void logout() {
        firebaseRemoteDataSource.logout();
    }

    @Override
    public FirebaseUser getCurrentUser() {
        return firebaseRemoteDataSource.getCurrentUser();
    }

    @Override
    public String getUserID() {
        return firebaseRemoteDataSource.getUserID();
    }

    @Override
    public void addFavouriteMeal(String userId, FavouriteMeal favouriteMeal) {
        firebaseRemoteDataSource.addFavouriteMeal(userId, favouriteMeal);
    }

    @Override
    public void addPlannedMeal(String userId, PlannedMeal plannedMeal) {
        firebaseRemoteDataSource.addPlannedMeal(userId, plannedMeal);
    }
}
