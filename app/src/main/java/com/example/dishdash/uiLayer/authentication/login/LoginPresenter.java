package com.example.dishdash.uiLayer.authentication.login;

import android.util.Log;
import android.util.Pair;
import android.util.Patterns;

import com.example.dishdash.dataLayer.dataSource.localDataSource.sharedPref.SharedPrefManager;
import com.example.dishdash.dataLayer.model.User;
import com.example.dishdash.dataLayer.repository.userRepo.FirebaseCallback;
import com.example.dishdash.dataLayer.repository.userRepo.FirebaseRepository;
import com.google.firebase.auth.FirebaseUser;

public class LoginPresenter implements LoginContract, FirebaseCallback {
    private final ILogin iLogin;
    private final FirebaseRepository firebaseRepository;
    private final SharedPrefManager sharedPrefManager;

    public LoginPresenter(ILogin iLogin, FirebaseRepository firebaseRepository, SharedPrefManager sharedPrefManager) {
        this.iLogin = iLogin;
        this.firebaseRepository = firebaseRepository;
        this.sharedPrefManager =sharedPrefManager;
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
    public void continueAsGuest() {
        sharedPrefManager.setUserId("GUEST");
    }

    @Override
    public void onSuccess(FirebaseUser user) {
        sharedPrefManager.setUserId(user.getUid());
        Log.d("TAG", "onSuccess: firebase  "+user.getUid());
        iLogin.onLoginSuccess();
    }

    @Override
    public void onFailure(String errorMessage) {
        iLogin.onLoginFail(errorMessage);
    }
}
