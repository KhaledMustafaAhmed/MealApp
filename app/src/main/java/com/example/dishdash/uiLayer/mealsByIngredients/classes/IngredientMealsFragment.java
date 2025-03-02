package com.example.dishdash.uiLayer.mealsByIngredients.classes;

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
import com.example.dishdash.uiLayer.mealsByCategory.classes.CategoryMealsFragmentArgs;
import com.example.dishdash.uiLayer.mealsByIngredients.interfaces.IIngredientMealsAdapter;
import com.example.dishdash.uiLayer.mealsByIngredients.interfaces.IngredientMealsView;

import java.util.ArrayList;
import java.util.List;


public class IngredientMealsFragment extends Fragment  implements IIngredientMealsAdapter , IngredientMealsView , Connection.NetworkCallbacksListener {


    private RecyclerView rv_meals_by_ingredient;
    private LottieAnimationView lottie_ingredient;
    private IngredientMealsAdapter ingredientMealsAdapter;
    private IngredientMealsPresenter ingredientMealsPresenter;
    private TextView tv_ingredient_meals_header;
    private Connection connection;
    private String name;

    public IngredientMealsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((HomeActivity) requireActivity()).showBottomNavigation(false);
        return inflater.inflate(R.layout.fragment_ingredient_meals, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        name = IngredientMealsFragmentArgs.fromBundle(getArguments()).getIngredientName();

        rv_meals_by_ingredient = view.findViewById(R.id.rv_meals_by_ingredient);
        lottie_ingredient = view.findViewById(R.id.lottie_ingredient);
        tv_ingredient_meals_header = view.findViewById(R.id.tv_ingredient_meals_header);

        ingredientMealsAdapter = new IngredientMealsAdapter(new ArrayList<>(), requireContext(), this);
        ingredientMealsPresenter = new IngredientMealsPresenter(MealsRepository.getInstance(MealsRemoteSourceImpl.getInstance(),
                MealsLocalSourceImpl.getInstance(requireContext())), this);

        setupRecycleView();
        connection = new Connection(requireContext(), this);

        /* Check network state */
        if(!connection.isNetworkAvailable()) {
            onConnectionUnAvailable();
        }
        connection.register();

        //lottie_category.setVisibility(View.VISIBLE);
    }

    private void setupRecycleView(){
        GridLayoutManager gridLayoutManager  = new GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, true);
        rv_meals_by_ingredient.setLayoutManager(gridLayoutManager);
        rv_meals_by_ingredient.setAdapter(ingredientMealsAdapter);
    }

    @Override
    public void onIngredientMealClicked(String meal_id) {
        Intent intent = new Intent(getActivity(), MealDetailsActivity.class);
        intent.putExtra("MEAL_ID", meal_id);
        startActivity(intent);
    }

    @Override
    public void receiveIngredientMeals(List<PopularItem> meals) {
        ingredientMealsAdapter.setMeals(meals);
    }

    private void hideAnimationAndShowPage(){
        new Handler(Looper.getMainLooper()).post(()->{
            tv_ingredient_meals_header.setVisibility(VISIBLE);
            rv_meals_by_ingredient.setVisibility(VISIBLE);
            lottie_ingredient.setVisibility(GONE);
        });
    }

    @Override
    public void onConnectionAvailable() {
        hideAnimationAndShowPage();
        ingredientMealsPresenter.getIngredientMeals(name);
        connection.unregister();
    }

    private void hidePageAndShowAnimation(){
        tv_ingredient_meals_header.setVisibility(GONE);
        rv_meals_by_ingredient.setVisibility(GONE);
        lottie_ingredient.setVisibility(VISIBLE);
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