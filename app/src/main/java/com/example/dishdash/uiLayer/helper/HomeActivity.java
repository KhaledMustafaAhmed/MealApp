package com.example.dishdash.uiLayer.helper;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.dishdash.R;
import com.example.dishdash.dataLayer.dataSource.localDataSource.sharedPref.SharedPrefManager;
import com.example.dishdash.dataLayer.dataSource.localDataSource.sharedPref.SharedPreferenceLocalDataSource;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavView;
    private NavHostFragment navHostFragment;
    private NavController navController;
    private SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sharedPrefManager = new SharedPrefManager(SharedPreferenceLocalDataSource.getInstance(this));
        bottomNavView = findViewById(R.id.bottom_nav_view);
        setupNavigation();
    }

    private void setupNavigation() {
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(bottomNavView, navController);
    }

    public void showBottomNavigation(boolean isVisible){
        bottomNavView.setVisibility(isVisible?VISIBLE:GONE);
    }
}