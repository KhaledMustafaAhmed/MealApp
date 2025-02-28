package com.example.dishdash.uiLayer.authentication.signup;

import android.util.Patterns;

import com.example.dishdash.dataLayer.model.User;
import com.example.dishdash.dataLayer.repository.userRepo.FirebaseCallback;
import com.example.dishdash.dataLayer.repository.userRepo.FirebaseRepository;
import com.example.dishdash.uiLayer.uiHelper.Triple;
import com.google.firebase.auth.FirebaseUser;

public class SignupPresenter implements  SignupContract, FirebaseCallback{
    private final FirebaseRepository firebaseRepository;
    private final ISignup iSignup;
    public SignupPresenter(ISignup iSignup,FirebaseRepository firebaseRepository){
        this.firebaseRepository = firebaseRepository;
        this.iSignup = iSignup;
    }

    @Override
    public void validateData(Triple<String, String, String> userInfo) {
        if(!Patterns.EMAIL_ADDRESS.matcher(userInfo.getFirst()).matches()){
            iSignup.onValidateFail(ISignup.EMAIL);
            return;
        }

        if(userInfo.getSecond().length()<6){
            iSignup.onValidateFail(ISignup.PASSWORD);
            return;
        }

        if(!userInfo.getSecond().equals(userInfo.getThird())){
            iSignup.onValidateFail(ISignup.CONFIRM_PASSWORD);
            return;
        }
        iSignup.onValidateSuccess(new User("no-name", userInfo.getFirst(), userInfo.getSecond()));
    }

    @Override
    public void doSignupWithFirebase(User user) {
        firebaseRepository.signupWithFirebase(user, this);
    }

    @Override
    public void onSuccess(FirebaseUser user) {
        iSignup.onSignupSuccess();

    }

    @Override
    public void onFailure(String errorMessage) {
        iSignup.onSignupFail(errorMessage);
    }
}
