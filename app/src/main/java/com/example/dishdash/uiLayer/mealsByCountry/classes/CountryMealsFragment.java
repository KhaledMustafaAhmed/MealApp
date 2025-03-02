package com.example.dishdash.uiLayer.mealsByCountry.classes;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.dishdash.uiLayer.helper.Connection;
import com.example.dishdash.uiLayer.helper.HomeActivity;
import com.example.dishdash.R;
import com.example.dishdash.dataLayer.dataSource.localDataSource.MealsLocalSourceImpl;
import com.example.dishdash.dataLayer.dataSource.remoteDataSource.mealsRemoteDataSource.classes.MealsRemoteSourceImpl;
import com.example.dishdash.dataLayer.model.pojo.popularCustomPojo.PopularItem;
import com.example.dishdash.dataLayer.repository.mealsRepo.MealsRepository;
import com.example.dishdash.uiLayer.mealDetails.MealDetailsActivity;
import com.example.dishdash.uiLayer.mealsByCountry.interfaces.ICountryMealsAdapter;
import com.example.dishdash.uiLayer.mealsByCountry.interfaces.ICountryMealsViews;

import java.util.ArrayList;
import java.util.List;

public class CountryMealsFragment extends Fragment implements ICountryMealsViews, ICountryMealsAdapter, Connection.NetworkCallbacksListener {

    private RecyclerView rv_meals_by_country;
    private CountryMealsAdapter countryMealsAdapter;
    private LottieAnimationView lottie_country;
    private TextView tv_country_meals_header;
    private CountryMealsPresenter countryMealsPresenter;
    private Connection connection;
    private String countryName;

    public CountryMealsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((HomeActivity) requireActivity()).showBottomNavigation(false);
        return inflater.inflate(R.layout.fragment_country_meals, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lottie_country = (LottieAnimationView) view.findViewById(R.id.lottie_country);

        tv_country_meals_header = (TextView) view.findViewById(R.id.tv_country_meals_header);

        rv_meals_by_country = (RecyclerView) view.findViewById(R.id.rv_meals_by_country);

        countryName = CountryMealsFragmentArgs.fromBundle(getArguments()).getCountryName();

        countryMealsPresenter = new CountryMealsPresenter(MealsRepository.getInstance(MealsRemoteSourceImpl.getInstance(),
                MealsLocalSourceImpl.getInstance(getContext())),
                this);

        countryMealsAdapter = new CountryMealsAdapter(this, requireContext(), new ArrayList<>());

        setupRecycleView();

        connection  =new Connection(requireContext(), this);

        /* Check network state */
        if(!connection.isNetworkAvailable()) {
            onConnectionUnAvailable();
        }

        connection.register();
    }

    private void setupRecycleView(){
        GridLayoutManager gridLayoutManager  = new GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, true);
        rv_meals_by_country.setLayoutManager(gridLayoutManager);
        rv_meals_by_country.setAdapter(countryMealsAdapter);
    }
    @Override
    public void receiveCountryMeals(List<PopularItem> meals) {
        countryMealsAdapter.setPopularList(meals);
    }

    @Override
    public void onCountryMealClicked(String mealID) {
        Intent intent = new Intent(getActivity(), MealDetailsActivity.class);
        intent.putExtra("MEAL_ID",mealID);
        startActivity(intent);
    }

    private void hideAnimationAndShowPage(){
        new Handler(Looper.getMainLooper()).post(()->{
            tv_country_meals_header.setVisibility(VISIBLE);
            rv_meals_by_country.setVisibility(VISIBLE);
            lottie_country.setVisibility(GONE);
        });
    }
    @Override
    public void onConnectionAvailable() {
        hideAnimationAndShowPage();
        countryMealsPresenter.getMealsBasedOnCountries(countryName);
        connection.unregister();
    }

    private void hidePageAndShowAnimation(){
        tv_country_meals_header.setVisibility(GONE);
        rv_meals_by_country.setVisibility(GONE);
        lottie_country.setVisibility(VISIBLE);
    }
    @Override
    public void onConnectionUnAvailable() {
        hidePageAndShowAnimation();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        connection.unregister();
    }
}