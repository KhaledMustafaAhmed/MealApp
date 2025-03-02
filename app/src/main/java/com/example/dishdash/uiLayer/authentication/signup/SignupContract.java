package com.example.dishdash.uiLayer.authentication.signup;

import com.example.dishdash.dataLayer.model.User;
import com.example.dishdash.uiLayer.helper.Triple;

public interface SignupContract {
    public void validateData(Triple<String, String, String> userInfo);
    public void doSignupWithFirebase(User user);
}
