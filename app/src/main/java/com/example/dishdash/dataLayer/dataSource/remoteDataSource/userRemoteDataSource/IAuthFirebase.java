package com.example.dishdash.dataLayer.dataSource.remoteDataSource.userRemoteDataSource;

import com.example.dishdash.dataLayer.model.User;
import com.example.dishdash.dataLayer.repository.userRepo.FirebaseCallback;

public interface IAuthFirebase {
    public void login(User user, FirebaseCallback callback);
    public void signup(User user, FirebaseCallback callback);

    public void logout();
}
