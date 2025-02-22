package com.example.dishdash.dataLayer.repository.userRepo;

import com.example.dishdash.dataLayer.dataSource.remoteDataSource.userRemoteDataSource.FirebaseRemoteDataSource;
import com.example.dishdash.dataLayer.model.User;
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
    public FirebaseUser getCurrentUser() {
        return  firebaseRemoteDataSource.getCurrentUser();
    }
}
