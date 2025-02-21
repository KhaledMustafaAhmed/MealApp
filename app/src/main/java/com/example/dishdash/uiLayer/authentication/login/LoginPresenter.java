package com.example.dishdash.uiLayer.authentication.login;

import android.util.Pair;
import android.util.Patterns;

import com.example.dishdash.dataLayer.model.User;
import com.example.dishdash.dataLayer.repository.firebaseRepo.FirebaseCallback;
import com.example.dishdash.dataLayer.repository.firebaseRepo.FirebaseRepository;
import com.google.firebase.auth.FirebaseUser;

public class LoginPresenter implements LoginContract, FirebaseCallback {
    ILogin iLogin;
    FirebaseRepository firebaseRepository;

    public LoginPresenter(ILogin iLogin, FirebaseRepository firebaseRepository) {
        this.iLogin = iLogin;
        this.firebaseRepository = firebaseRepository;
    }

    @Override
    public void validateData(Pair<String, String> userInfo) {
        if (!Patterns.EMAIL_ADDRESS.matcher(userInfo.first.trim()).matches()) {
            iLogin.onValidateFail(ILogin.EMAIL);
            return;
        }

        if (userInfo.second.trim().length() < 6) {
            iLogin.onValidateFail(ILogin.PASSWORD);
            return;
        }
        iLogin.onValidateSuccess(new User("no-name", userInfo.first.trim(), userInfo.second.trim()));
    }

    @Override
    public void doLoginWithFirebase(User user) {
        firebaseRepository.loginWithFirebase(user, this);
    }

    @Override
    public void onSuccess(FirebaseUser user) {
        iLogin.onLoginSuccess();
    }

    @Override
    public void onFailure(String errorMessage) {
        iLogin.onLoginFail(errorMessage);
    }
}
