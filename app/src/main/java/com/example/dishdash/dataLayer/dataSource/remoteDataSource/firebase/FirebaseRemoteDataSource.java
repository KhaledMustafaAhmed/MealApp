package com.example.dishdash.dataLayer.dataSource.remoteDataSource.firebase;

import com.example.dishdash.dataLayer.model.User;
import com.example.dishdash.dataLayer.repository.firebaseRepo.FirebaseCallback;

public class FirebaseRemoteDataSource {
    private FirebaseAuth firebaseAuth;
    public FirebaseRemoteDataSource(){
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void login(User user, FirebaseCallback callback){
        /* login logic */
    }
}
