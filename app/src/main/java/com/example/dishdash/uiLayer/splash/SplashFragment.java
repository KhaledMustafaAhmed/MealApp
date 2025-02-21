package com.example.dishdash.uiLayer.splash;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dishdash.HomeActivity;
import com.example.dishdash.MainActivity;
import com.example.dishdash.R;
import com.example.dishdash.dataLayer.dataSource.remoteDataSource.firebase.FirebaseRemoteDataSource;
import com.example.dishdash.dataLayer.repository.firebaseRepo.FirebaseRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashFragment extends Fragment {
    SplashPresenter splashPresenter;

    public SplashFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        splashPresenter = new SplashPresenter(new FirebaseRepository(new FirebaseRemoteDataSource()));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(splashPresenter.getCurrentUser() != null){
                    startActivity(new Intent(getContext(), HomeActivity.class));
                    getActivity().finish();
                }else{
                    Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_loginFragment);
                }
            }
        },2000);
    }
}