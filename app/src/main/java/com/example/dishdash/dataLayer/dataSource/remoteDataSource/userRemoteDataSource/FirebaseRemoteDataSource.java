package com.example.dishdash.dataLayer.dataSource.remoteDataSource.userRemoteDataSource;

import androidx.annotation.NonNull;

import com.example.dishdash.dataLayer.model.User;
import com.example.dishdash.dataLayer.repository.userRepo.FirebaseCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseRemoteDataSource implements IAuthFirebase {
    FirebaseAuth firebaseAuth;

    public FirebaseRemoteDataSource(){
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void login(User user, FirebaseCallback firebaseCallback){
        firebaseAuth.signInWithEmailAndPassword(user.getEmail(),user.getPassword()).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                            if(firebaseUser.isEmailVerified()){
                                firebaseCallback.onSuccess(firebaseUser);
                            }else{
                                firebaseCallback.onFailure("Email is not verified, Please verify your email.");
                            }
                        }else{
                            firebaseCallback.onFailure("Login failed. Please try again.");
                        }
                    }
        });
    }
    @Override
    public void signup(User user, FirebaseCallback callback) {
        firebaseAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener((new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                            firebaseUser.sendEmailVerification();
                            callback.onSuccess(firebaseUser);
                        }else{
                            callback.onFailure("Signup Failed try again!");
                        }
                    }
                }));
    }

    @Override
    public void logout() {
        firebaseAuth.signOut();
    }


    public FirebaseUser getCurrentUser(){
        return  firebaseAuth.getCurrentUser();
    }
}
