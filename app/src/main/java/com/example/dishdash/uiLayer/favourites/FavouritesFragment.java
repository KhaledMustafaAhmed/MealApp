package com.example.dishdash.uiLayer.favourites;

import android.app.AlertDialog;
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

import com.airbnb.lottie.LottieAnimationView;
import com.example.dishdash.uiLayer.helper.HomeActivity;
import com.example.dishdash.R;
import com.example.dishdash.dataLayer.dataSource.localDataSource.MealsLocalSourceImpl;
import com.example.dishdash.dataLayer.dataSource.localDataSource.sharedPref.SharedPrefManager;
import com.example.dishdash.dataLayer.dataSource.localDataSource.sharedPref.SharedPreferenceLocalDataSource;
import com.example.dishdash.dataLayer.dataSource.remoteDataSource.mealsRemoteDataSource.classes.MealsRemoteSourceImpl;
import com.example.dishdash.dataLayer.dataSource.remoteDataSource.userRemoteDataSource.FirebaseRemoteDataSource;
import com.example.dishdash.dataLayer.model.entities.FavouriteMeal;
import com.example.dishdash.dataLayer.repository.mealsRepo.MealsRepository;
import com.example.dishdash.dataLayer.repository.userRepo.FirebaseRepository;
import com.example.dishdash.uiLayer.mealDetails.MealDetailsActivity;
import com.example.dishdash.uiLayer.helper.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

public class FavouritesFragment extends Fragment implements IFavouriteAdapter, IFavouritesView{
    private FavouriteMealsAdapter favouriteMealsAdapter;
    private RecyclerView rv_favourites_fragment;
    private FavouritesMealsPresenter favouritesMealsPresenter;
    private LottieAnimationView lottie_fav;

    public FavouritesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((HomeActivity) requireActivity()).showBottomNavigation(true);
        return inflater.inflate(R.layout.fragment_favourites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lottie_fav = view.findViewById(R.id.lottie_fav);
        rv_favourites_fragment = view.findViewById(R.id.rv_favourites_fragment);

        favouritesMealsPresenter = new FavouritesMealsPresenter(this,MealsRepository.getInstance(MealsRemoteSourceImpl.getInstance(),
                MealsLocalSourceImpl.getInstance(requireContext())),
                new FirebaseRepository(new FirebaseRemoteDataSource()), new SharedPrefManager(SharedPreferenceLocalDataSource.getInstance(requireContext())));

        favouriteMealsAdapter = new FavouriteMealsAdapter(new ArrayList<>(), requireContext(), this, new GlideImageLoader(requireContext()));

        setUpRecycleView();

        favouritesMealsPresenter.checkUserMode();
    }

    private void setUpRecycleView(){
        GridLayoutManager gridLayoutManager  = new GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false);
        rv_favourites_fragment.setLayoutManager(gridLayoutManager);
        rv_favourites_fragment.setAdapter(favouriteMealsAdapter);
    }

    @Override
    public void onDeleteFavItemClicked(String meal_id) {
        createDialog(meal_id).show();
    }

    @Override
    public void onFavItemClicked(String meal_id) {
        Intent intent = new Intent(getActivity(), MealDetailsActivity.class);
        intent.putExtra("MEAL_ID", meal_id);
        startActivity(intent);
    }

    @Override
    public void receiveFavouritesItem(List<FavouriteMeal> favouriteMeals) {
        favouriteMealsAdapter.setMealsList(favouriteMeals);
    }

    @Override
    public void deletionSuccess() {
        favouritesMealsPresenter.getFavouritesItems();
    }

    @Override
    public void showAnimation() {
        lottie_fav.setVisibility(View.VISIBLE);
        lottie_fav.playAnimation();
    }

    @Override
    public void getUserData() {
        lottie_fav.setVisibility(View.GONE);
        favouritesMealsPresenter.getFavouritesItems();
    }

    private AlertDialog createDialog(String meal_id){
        AlertDialog.Builder builder = new AlertDialog.Builder((getContext()));
        builder.setMessage("Do you want to delete your meal?");
        builder.setPositiveButton("Yes", ((dialog, which) -> {favouritesMealsPresenter.deleteFavouriteItem(meal_id);}));
        builder.setNegativeButton("No Please", ((dialog, which) -> {}));
        return builder.create();
    }
}