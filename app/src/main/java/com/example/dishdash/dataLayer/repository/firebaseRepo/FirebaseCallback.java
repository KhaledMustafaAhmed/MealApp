package com.example.dishdash.dataLayer.repository.firebaseRepo;

public interface FirebaseCallback {
    public void onSuccess(FirebaseUser user);
    public void onFailure(String errorMessage);
}
