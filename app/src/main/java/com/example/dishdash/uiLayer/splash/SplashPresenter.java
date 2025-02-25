package com.example.dishdash.uiLayer.splash;

import com.example.dishdash.dataLayer.repository.userRepo.FirebaseRepository;
import com.google.firebase.auth.FirebaseUser;

public class SplashPresenter implements SplashContract{
    FirebaseRepository firebaseRepository;

    public SplashPresenter(FirebaseRepository firebaseRepository){
        this.firebaseRepository = firebaseRepository;
    }
    @Override
    public FirebaseUser getCurrentUser() {
        return firebaseRepository.getCurrentUser();
    }
}
