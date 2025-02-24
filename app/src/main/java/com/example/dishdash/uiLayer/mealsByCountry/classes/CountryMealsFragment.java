package com.example.dishdash.uiLayer.mealsByCountry.classes;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dishdash.R;
import com.example.dishdash.dataLayer.dataSource.remoteDataSource.mealsRemoteDataSource.classes.MealsRemoteSourceImpl;
import com.example.dishdash.dataLayer.model.pojo.popularCustomPojo.PopularItem;
import com.example.dishdash.dataLayer.repository.mealsRepo.MealsRepository;
import com.example.dishdash.uiLayer.mealDetails.MealDetailsActivity;
import com.example.dishdash.uiLayer.mealsByCategory.classes.CategoryMealsFragmentArgs;
import com.example.dishdash.uiLayer.mealsByCountry.interfaces.ICountryMealsAdapter;
import com.example.dishdash.uiLayer.mealsByCountry.interfaces.ICountryMealsViews;

import java.util.ArrayList;
import java.util.List;

public class CountryMealsFragment extends Fragment implements ICountryMealsViews, ICountryMealsAdapter{

    RecyclerView rv_meals_by_country;
    CountryMealsAdapter countryMealsAdapter;
    CountryMealsPresenter countryMealsPresenter;
    public CountryMealsFragment() {
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
        return inflater.inflate(R.layout.fragment_country_meals, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String countryName = CountryMealsFragmentArgs.fromBundle(getArguments()).getCountryName();
        rv_meals_by_country = (RecyclerView) view.findViewById(R.id.rv_meals_by_country);
        countryMealsAdapter = new CountryMealsAdapter(this, requireContext(), new ArrayList<>());
        setupRecycleView();
        countryMealsPresenter = new CountryMealsPresenter(MealsRepository.getInstance(MealsRemoteSourceImpl.getInstance()),
                this);
        countryMealsPresenter.getMealsBasedOnCountries(countryName);
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
}