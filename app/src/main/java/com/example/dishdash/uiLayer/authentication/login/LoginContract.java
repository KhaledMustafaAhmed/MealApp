package com.example.dishdash.uiLayer.authentication.login;

import android.util.Pair;

import com.example.dishdash.dataLayer.model.User;

import java.net.PortUnreachableException;

public interface LoginContract {
    public void validateData(Pair<String, String> userInfo);
    public void doLoginWithFirebase(User user);

    public void saveUserInfo(String user_Id);

    public String getUserID();

    //TODO Add function doLoginWithGoogle
}
