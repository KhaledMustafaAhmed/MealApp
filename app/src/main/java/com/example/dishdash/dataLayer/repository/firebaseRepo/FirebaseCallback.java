package com.example.dishdash.dataLayer.repository.firebaseRepo;

import com.google.firebase.auth.FirebaseUser;

public interface FirebaseCallback {
    public void onSuccess(FirebaseUser user);
    public void onFailure(String errorMessage);
}
