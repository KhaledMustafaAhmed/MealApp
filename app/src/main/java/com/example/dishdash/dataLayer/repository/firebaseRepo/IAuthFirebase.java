package com.example.dishdash.dataLayer.repository.firebaseRepo;

import com.example.dishdash.dataLayer.model.User;

public interface IAuthFirebase {
    public void login(User user, FirebaseCallback callback);
    public void signup(User user, FirebaseCallback callback);
}
