package com.example.dishdash.uiLayer.mealsByCategory.classes;

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
import com.example.dishdash.uiLayer.mealsByCategory.interfaces.ICategoryMealsAdapter;
import com.example.dishdash.uiLayer.mealsByCategory.interfaces.ICategoryMealsView;

import java.util.ArrayList;
import java.util.List;

public class CategoryMealsFragment extends Fragment implements ICategoryMealsAdapter , ICategoryMealsView {

    private RecyclerView rv_meals_by_category;
    private CategoryMealsAdapter categoryMealsAdapter;
    private CategoryMealsPresenter categoryMealsPresenter;

    public CategoryMealsFragment() {
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
        return inflater.inflate(R.layout.fragment_category_meals, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String name = CategoryMealsFragmentArgs.fromBundle(getArguments()).getCategoryName();
        rv_meals_by_category = (RecyclerView) view.findViewById(R.id.rv_meals_by_category);
        categoryMealsAdapter = new CategoryMealsAdapter(this, requireContext(), new ArrayList<>());
        categoryMealsPresenter = new CategoryMealsPresenter(this,
                MealsRepository.getInstance(MealsRemoteSourceImpl.getInstance()));
        setupRecycleView();
        categoryMealsPresenter.getCategoryMeals(name);
    }

    private void setupRecycleView(){
        GridLayoutManager gridLayoutManager  = new GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, true);
        rv_meals_by_category.setLayoutManager(gridLayoutManager);
        rv_meals_by_category.setAdapter(categoryMealsAdapter);
    }

    @Override
    public void onCategoryMealClicked(String mealID) {
        Intent intent = new Intent(getActivity(), MealDetailsActivity.class);
        intent.putExtra("MEAL_ID", mealID);
        startActivity(intent);
    }

    @Override
    public void receiveCategoryMeals(List<PopularItem> meals) {
        categoryMealsAdapter.setMeals(meals);
    }
}