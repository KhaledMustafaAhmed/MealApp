package com.example.dishdash.uiLayer.home.classes;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dishdash.R;
import com.example.dishdash.dataLayer.dataSource.remoteDataSource.mealsRemoteDataSource.classes.MealsRemoteSourceImpl;
import com.example.dishdash.dataLayer.model.pojo.mealsList.MeaList;
import com.example.dishdash.dataLayer.model.pojo.popularCustomPojo.PopularItem;
import com.example.dishdash.dataLayer.repository.mealsRepo.MealsRepository;
import com.example.dishdash.uiLayer.mealDetails.MealDetailsActivity;
import com.example.dishdash.uiLayer.home.adapters.PopularAdapter;
import com.example.dishdash.uiLayer.home.interfaces.IHomeView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements IHomeView {
    private static final String TAG = "HomeFragment";
    Button btn_logout;
    CardView cv_random_meal;
    ImageView iv_random_meal;
    TextView tv_random_meal_name;
    HomePresenter homePresenter;
    PopularAdapter popularAdapter;
    RecyclerView recyclerView;
    MeaList meaList;

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
        cv_random_meal= view.findViewById(R.id.cv_random_meal);
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
        cv_random_meal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MealDetailsActivity.class);
                intent.putExtra("MEAL_ID", meaList.getMeals().get(0).getIdMeal());
                startActivity(intent);
            }
        });

        btn_logout = (Button) view.findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePresenter.logout();
            }
        });
    }

    @Override
    public void receiveRandoMeal(MeaList meaList) {
        this.meaList = meaList;
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

    @Override
    public void doLogout() {
        //TODO NAVIGATE TO LOGIN SCREEN
    }

}