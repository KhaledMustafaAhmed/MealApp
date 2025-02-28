package com.example.dishdash.dataLayer.dataSource.remoteDataSource.userRemoteDataSource;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.dishdash.dataLayer.model.User;
import com.example.dishdash.dataLayer.model.entities.FavouriteMeal;
import com.example.dishdash.dataLayer.model.entities.PlannedMeal;
import com.example.dishdash.dataLayer.repository.userRepo.FirebaseCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

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

    @Override
    public FirebaseUser getCurrentUser(){
        return firebaseAuth.getCurrentUser();
    }

    public  String getUserID() {
        return firebaseAuth.getCurrentUser().getUid();
    }


    public void addFavouriteMeal(String userId, FavouriteMeal favouriteMeal) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference favouritesRef = db.collection("users").document(userId).collection("favouriteMeals")
                .document().collection(favouriteMeal.getMeal_id());

        favouritesRef.document(favouriteMeal.getMeal_id()).set(favouriteMeal)
                .addOnSuccessListener(aVoid -> {
                    Log.d("TAG", "addFavouriteMeal: success");
                })
                .addOnFailureListener(e -> {
                    Log.d("TAG", "addFavouriteMeal: error "+e.getMessage());
                });
    }

    //TODO need to change
    public void addPlannedMeal(String userId, PlannedMeal plannedMeal) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        CollectionReference plannedRef = db.collection("users").document(userId).collection("plannedMeals");

        plannedRef.document(plannedMeal.getMeal_id()).set(plannedMeal)
                .addOnSuccessListener(aVoid -> {
                    Log.d("TAG", "addFavouriteMeal: success");
                })
                .addOnFailureListener(e -> {
                    Log.d("TAG", "addFavouriteMeal: error");
                });
    }
}
