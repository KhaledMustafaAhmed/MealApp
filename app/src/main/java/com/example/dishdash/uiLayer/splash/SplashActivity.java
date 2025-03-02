package com.example.dishdash.uiLayer.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.dishdash.uiLayer.helper.HomeActivity;
import com.example.dishdash.MainActivity;
import com.example.dishdash.R;
import com.example.dishdash.dataLayer.dataSource.localDataSource.sharedPref.SharedPrefManager;
import com.example.dishdash.dataLayer.dataSource.localDataSource.sharedPref.SharedPreferenceLocalDataSource;

public class SplashActivity extends AppCompatActivity{
    private ImageView iv_splash_logo, iv_splash_image;
    private TextView tv_splash_header;
    private LottieAnimationView lottie;
    private static final int SPLASH_TIME_OUT = 6100;
    private SplashPresenter splashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashPresenter = new SplashPresenter(new SharedPrefManager(SharedPreferenceLocalDataSource.getInstance(this)));

        initUI();
        startAnimation();

        new Handler().postDelayed(()->{
            String user =  splashPresenter.checkUserMode();
            if(user == null || user.equals("GUEST")){
                startActivity(new Intent(this, MainActivity.class));
            }else{
                startActivity(new Intent(this, HomeActivity.class));
            }
            finish();
        },SPLASH_TIME_OUT);
    }

    private void initUI(){
        iv_splash_image =(ImageView) findViewById(R.id.iv_splash_image);
        iv_splash_logo =(ImageView) findViewById(R.id.iv_splash_logo);
        tv_splash_header =(TextView) findViewById(R.id.tv_splash_header);
        lottie =(LottieAnimationView) findViewById(R.id.lottie);
    }

    private void startAnimation(){
        iv_splash_image.animate().translationY(-2500).setDuration(1000).setStartDelay(5000);
        iv_splash_logo.animate().translationY(2000).setDuration(1000).setStartDelay(5000);
        tv_splash_header.animate().translationY(2000).setStartDelay(1000).setStartDelay(5000);
        lottie.animate().translationY(1800).setDuration(1000).setStartDelay(5000);
    }
}