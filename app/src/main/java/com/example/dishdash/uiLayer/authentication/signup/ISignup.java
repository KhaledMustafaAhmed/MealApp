package com.example.dishdash.uiLayer.authentication.signup;

import com.example.dishdash.dataLayer.model.User;

public interface ISignup {
    public static final int EMAIL = 1;
    public static final int PASSWORD = 2;
    public static final int CONFIRM_PASSWORD = 3;
    public void showProgressBar();
    public void hideProgressBar();
    public void onSignupSuccess();
    public void onSignupFail(String errorMessage);
    public void onValidateSuccess(User user);
    public void onValidateFail(int message);
}
