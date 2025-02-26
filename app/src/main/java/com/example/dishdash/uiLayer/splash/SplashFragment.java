package com.example.dishdash.uiLayer.splash;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.dishdash.HomeActivity;
import com.example.dishdash.R;
import com.example.dishdash.dataLayer.dataSource.localDataSource.sharedPref.SharedPrefManager;
import com.example.dishdash.dataLayer.dataSource.localDataSource.sharedPref.SharedPreferenceLocalDataSource;
import com.example.dishdash.dataLayer.dataSource.remoteDataSource.userRemoteDataSource.FirebaseRemoteDataSource;
import com.example.dishdash.dataLayer.repository.userRepo.FirebaseRepository;

public class SplashFragment extends Fragment implements ISplashView {
    SplashPresenter splashPresenter;
    ImageView iv_splash_image, iv_splash_logo;
    LottieAnimationView lottie;
    boolean var = false;
    View _view;

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
        _view = view;
        iv_splash_image = view.findViewById(R.id.iv_splash_image);
        iv_splash_logo = view.findViewById(R.id.iv_splash_logo);
        lottie = view.findViewById(R.id.lottie);

        splashPresenter = new SplashPresenter(this, new FirebaseRepository(new FirebaseRemoteDataSource()),
                new SharedPrefManager(SharedPreferenceLocalDataSource.getInstance(getContext())));
        iv_splash_image.animate().translationY(-1600).setDuration(2000);
        iv_splash_logo.animate().translationY(1400).setDuration(2000);
        lottie.animate().translationY(1400).setDuration(2000);
        Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_loginFragment);

        splashPresenter.checkUser();
      /*  new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        },5000);
    }*/

    /*@Override
    public void receiveCurrentUserID(String user_id) {
        if(user_id != null){
            Log.d("TAG", "user id: "+splashPresenter.getCurrentUser().getUid());
            startActivity(new Intent(getContext(), HomeActivity.class));
            getActivity().finish();
        }else{
            Navigation.findNavController(getView()).navigate(R.id.action_splashFragment_to_loginFragment);
        }
    }*/
/*
        @Override
        public void userFound () {
            Log.d("TAG", "userFound: ");
            startActivity(new Intent(getContext(), HomeActivity.class));
            getActivity().finish();
        }

        @Override
        public void userNotFound () {
            Log.d("TAG", "userNotFound: ");
            Navigation.findNavController(_view).navigate(R.id.action_splashFragment_to_loginFragment);
        }*/
/*
    if(splashPresenter.getCurrentUser() != null){
        Log.d("TAG", "user id: "+splashPresenter.getCurrentUser().getUid());
        startActivity(new Intent(getContext(), HomeActivity.class));
        getActivity().finish();
    }else{
        Navigation.findNavController(getView()).navigate(R.id.action_splashFragment_to_loginFragment);
    }*/
    }

    @Override
    public void userFound() {

    }

    @Override
    public void userNotFound() {

    }
}