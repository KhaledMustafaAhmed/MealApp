package com.example.dishdash.dataLayer.repository.firebaseRepo;

import com.example.dishdash.dataLayer.dataSource.remoteDataSource.firebase.FirebaseRemoteDataSource;
import com.example.dishdash.dataLayer.model.User;

public class FirebaseRepository implements IAuthFirebase {
    private FirebaseRemoteDataSource firebaseRemoteDataSource;

    public FirebaseRepository(FirebaseRemoteDataSource _firebaseRemoteDataSource){
        firebaseRemoteDataSource = _firebaseRemoteDataSource;
    }
    @Override
    public void login(User user, FirebaseCallback callback) {
        firebaseRemoteDataSource.login(user, callback);
    }
}
