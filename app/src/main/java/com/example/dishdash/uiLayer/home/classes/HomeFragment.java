package com.example.dishdash.uiLayer.home.classes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.L;
import com.bumptech.glide.Glide;
import com.example.dishdash.R;
import com.example.dishdash.dataLayer.dataSource.remoteDataSource.mealsRemoteDataSource.classes.MealsRemoteSourceImpl;
import com.example.dishdash.dataLayer.model.pojo.mealsList.MeaList;
import com.example.dishdash.dataLayer.model.pojo.popularCustomPojo.PopularItem;
import com.example.dishdash.dataLayer.model.pojo.popularCustomPojo.PopularList;
import com.example.dishdash.dataLayer.repository.mealsRepo.MealsRepository;
import com.example.dishdash.uiLayer.home.adapters.PopularAdapter;
import com.example.dishdash.uiLayer.home.interfaces.IHomeView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements IHomeView {
    private static final String TAG = "HomeFragment";
    ImageView iv_random_meal;
    TextView tv_random_meal_name;
    HomePresenter homePresenter;
    PopularAdapter popularAdapter;
    RecyclerView recyclerView;

    public HomeFragment() {
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
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iv_random_meal = view.findViewById(R.id.iv_random_meal);
        tv_random_meal_name = view.findViewById(R.id.tv_random_meal_name);
        recyclerView = view.findViewById(R.id.rv_popular_meals);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        homePresenter = new HomePresenter(this, MealsRepository.getInstance(MealsRemoteSourceImpl.getInstance()));
        popularAdapter = new PopularAdapter(requireContext(),new ArrayList<>());
        recyclerView.setAdapter(popularAdapter);
        popularAdapter.notifyDataSetChanged();
        homePresenter.getRandoMeal();
        homePresenter.getPopularItems("Beef");
    }

    @Override
    public void receiveRandoMeal(MeaList meaList) {
        Log.e(TAG, "receiveRandoMeal: " );
        tv_random_meal_name.setText(meaList.getMeals().get(0).getStrMeal());
        Glide.with(requireContext())
                .load(meaList.getMeals().get(0).getStrMealThumb())
                .into(iv_random_meal);
    }

    @Override
    public void receivePopularItems(List<PopularItem> popularList) {
        Log.e(TAG, "receivePopularItems: " );
        popularAdapter.setPopularList(popularList);
    }
}