package com.example.dishdash.dataLayer.repository.firebaseRepo;

import com.example.dishdash.dataLayer.model.User;
import com.google.firebase.auth.FirebaseUser;

public interface IFirebaseRepo {
    public void loginWithFirebase(User user, FirebaseCallback callback);
    public void signupWithFirebase(User user, FirebaseCallback callback);
    public FirebaseUser getCurrentUser();
}
