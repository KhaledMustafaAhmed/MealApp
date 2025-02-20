package com.example.dishdash.uiLayer.authentication.login;

import android.util.Pair;

import com.example.dishdash.dataLayer.model.User;

public interface LoginContract {
    public void validateData(Pair<String, String> userInfo);
    public void doLoginWithFirebase(User user);
}
