package com.example.dishdash.uiLayer.authentication.login;

import com.example.dishdash.dataLayer.model.User;

public interface ILogin {
    public static final int EMAIL = 1;
    public static final int PASSWORD = 2;
    public static final int BOTH = 3;
    public void showProgressBar();
    public void hideProgressBar();
    public void onLoginSuccess();
    public void onLoginFail(String errorMessage);
    public void onValidateSuccess(User user);
    public void onValidateFail(int message);
}
