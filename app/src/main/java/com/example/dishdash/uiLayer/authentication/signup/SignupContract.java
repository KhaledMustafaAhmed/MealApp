package com.example.dishdash.uiLayer.authentication.signup;

import android.util.Pair;

import com.example.dishdash.dataLayer.model.User;
import com.example.dishdash.uiLayer.uiHelper.Triple;

public interface SignupContract {
    public void validateData(Triple<String, String, String> userInfo);
    public void doSignupWithFirebase(User user);
}
