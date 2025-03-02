package com.example.dishdash.uiLayer.mealsByCategory.classes;

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
import com.example.dishdash.Connection;
import com.example.dishdash.HomeActivity;
import com.example.dishdash.R;
import com.example.dishdash.dataLayer.dataSource.localDataSource.MealsLocalSourceImpl;
import com.example.dishdash.dataLayer.dataSource.remoteDataSource.mealsRemoteDataSource.classes.MealsRemoteSourceImpl;
import com.example.dishdash.dataLayer.model.pojo.popularCustomPojo.PopularItem;
import com.example.dishdash.dataLayer.repository.mealsRepo.MealsRepository;
import com.example.dishdash.uiLayer.mealDetails.MealDetailsActivity;
import com.example.dishdash.uiLayer.mealsByCategory.interfaces.ICategoryMealsAdapter;
import com.example.dishdash.uiLayer.mealsByCategory.interfaces.ICategoryMealsView;

import java.util.ArrayList;
import java.util.List;

public class CategoryMealsFragment extends Fragment implements ICategoryMealsAdapter , ICategoryMealsView, Connection.NetworkCallbacksListener {

    private RecyclerView rv_meals_by_category;
    private CategoryMealsAdapter categoryMealsAdapter;
    private CategoryMealsPresenter categoryMealsPresenter;
    private LottieAnimationView lottie_category;
    private TextView tv_category_meals_header;
    private String name;
    private Connection connection;

    public CategoryMealsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((HomeActivity) requireActivity()).showBottomNavigation(false);
        return inflater.inflate(R.layout.fragment_category_meals, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        name = CategoryMealsFragmentArgs.fromBundle(getArguments()).getCategoryName();

        rv_meals_by_category = (RecyclerView) view.findViewById(R.id.rv_meals_by_category);
        lottie_category = (LottieAnimationView) view.findViewById(R.id.lottie_category);
        tv_category_meals_header = (TextView) view.findViewById(R.id.tv_category_meals_header);

        categoryMealsAdapter = new CategoryMealsAdapter(this, requireContext(), new ArrayList<>());

        categoryMealsPresenter = new CategoryMealsPresenter(this,
                MealsRepository.getInstance(MealsRemoteSourceImpl.getInstance(), MealsLocalSourceImpl.getInstance(getContext())));
        setupRecycleView();

        connection = new Connection(requireContext(), this);

        /* Check network state */
        if(!connection.isNetworkAvailable()) {
            onConnectionUnAvailable();
        }
        connection.register();
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
    private void hideAnimationAndShowPage(){
        new Handler(Looper.getMainLooper()).post(()->{
            tv_category_meals_header.setVisibility(VISIBLE);
            rv_meals_by_category.setVisibility(VISIBLE);
            lottie_category.setVisibility(GONE);
        });
    }

    @Override
    public void onConnectionAvailable() {
        categoryMealsPresenter.getCategoryMeals(name);
        connection.unregister();
    }

    private void hidePageAndShowAnimation(){
        tv_category_meals_header.setVisibility(GONE);
        rv_meals_by_category.setVisibility(GONE);
        lottie_category.setVisibility(VISIBLE);
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