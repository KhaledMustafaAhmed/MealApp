package com.example.dishdash.uiLayer.authentication.login;

import android.util.Pair;

import com.example.dishdash.dataLayer.model.User;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.net.PortUnreachableException;

public interface LoginContract {
    public void validateData(Pair<String, String> userInfo);
    public void doLoginWithFirebase(User user);

    public void continueAsGuest();

    //TODO Add function doLoginWithGoogle
    public void doLoginWithGoogle(GoogleSignInAccount account);
}
