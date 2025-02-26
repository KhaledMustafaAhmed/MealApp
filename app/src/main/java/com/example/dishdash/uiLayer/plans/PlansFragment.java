package com.example.dishdash.uiLayer.plans;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dishdash.R;
import com.example.dishdash.dataLayer.dataSource.localDataSource.MealsLocalSourceImpl;
import com.example.dishdash.dataLayer.dataSource.remoteDataSource.mealsRemoteDataSource.classes.MealsRemoteSourceImpl;
import com.example.dishdash.dataLayer.dataSource.remoteDataSource.userRemoteDataSource.FirebaseRemoteDataSource;
import com.example.dishdash.dataLayer.model.entities.PlannedMeal;
import com.example.dishdash.dataLayer.repository.mealsRepo.MealsRepository;
import com.example.dishdash.dataLayer.repository.userRepo.FirebaseRepository;
import com.example.dishdash.uiLayer.mealDetails.MealDetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class PlansFragment extends Fragment implements IPlansAdapter, IPlansViews {
    private RecyclerView rv_weekly_plan_meals;
    private PlansAdapter plansAdapter;
    private PlansPresenter plansPresenter;

    public PlansFragment() {
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
        return inflater.inflate(R.layout.fragment_plans, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        plansPresenter = new PlansPresenter(MealsRepository.getInstance(MealsRemoteSourceImpl.getInstance(),
                MealsLocalSourceImpl.getInstance(requireContext())),
                new FirebaseRepository(new FirebaseRemoteDataSource()),
                this);

        plansAdapter = new PlansAdapter(requireContext(), this, new ArrayList<>());

        rv_weekly_plan_meals = (RecyclerView) view.findViewById(R.id.rv_weekly_plan_meals);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_weekly_plan_meals.setLayoutManager(linearLayoutManager);
        rv_weekly_plan_meals.setAdapter(plansAdapter);

        plansPresenter.getAllPlannedMealsForUser();
    }

    @Override
    public void onDelPlanClicked(String meal_id, String date) {
        createDialog(meal_id, date).show();
    }

    @Override
    public void onPlanItemClicked(String meal_id) {
        Intent intent = new Intent(getActivity(), MealDetailsActivity.class);
        intent.putExtra("MEAL_ID", meal_id);
        startActivity(intent);
    }

    @Override
    public void showPlannedDeleted() {
        plansPresenter.getAllPlannedMealsForUser();
        Toast.makeText(getContext(), "Deleted Success!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void receiveAllPlannedMeals(List<PlannedMeal> plannedMeals) {
        plansAdapter.setPlannedMeals(plannedMeals);
    }


    private AlertDialog createDialog(String meal_id, String date){
        AlertDialog.Builder builder = new AlertDialog.Builder((getContext()));
        builder.setMessage("Do you want to delete your meal?");
        builder.setPositiveButton("Yes", ((dialog, which) -> {plansPresenter.gelPlannedMeal(meal_id, date);}));
        builder.setNegativeButton("No Please", ((dialog, which) -> {}));
        return builder.create();
    }
}