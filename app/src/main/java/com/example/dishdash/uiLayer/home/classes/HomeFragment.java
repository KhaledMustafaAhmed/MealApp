package com.example.dishdash.uiLayer.home.classes;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dishdash.MainActivity;
import com.example.dishdash.R;
import com.example.dishdash.dataLayer.dataSource.localDataSource.MealsLocalSourceImpl;
import com.example.dishdash.dataLayer.dataSource.remoteDataSource.mealsRemoteDataSource.classes.MealsRemoteSourceImpl;
import com.example.dishdash.dataLayer.dataSource.remoteDataSource.userRemoteDataSource.FirebaseRemoteDataSource;
import com.example.dishdash.dataLayer.model.pojo.areaCustomPojo.CountryItem;
import com.example.dishdash.dataLayer.model.pojo.categoryCustomPojo.CategoryItem;
import com.example.dishdash.dataLayer.model.pojo.mealsList.MeaList;
import com.example.dishdash.dataLayer.model.pojo.popularCustomPojo.PopularItem;
import com.example.dishdash.dataLayer.repository.mealsRepo.MealsRepository;
import com.example.dishdash.dataLayer.repository.userRepo.FirebaseRepository;
import com.example.dishdash.uiLayer.home.adapters.CategoryAdapter;
import com.example.dishdash.uiLayer.home.adapters.CountryAdapter;
import com.example.dishdash.uiLayer.home.interfaces.ICategory;
import com.example.dishdash.uiLayer.home.interfaces.ICountry;
import com.example.dishdash.uiLayer.home.interfaces.IPopular;
import com.example.dishdash.uiLayer.mealDetails.MealDetailsActivity;
import com.example.dishdash.uiLayer.home.adapters.PopularAdapter;
import com.example.dishdash.uiLayer.home.interfaces.IHomeView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements IHomeView, ICategory, ICountry, IPopular {
    private static final String TAG = "HomeFragment";
    Button btn_logout;
    CardView cv_random_meal;
    ImageView iv_random_meal;
    TextView tv_random_meal_name;
    HomePresenter homePresenter;
    PopularAdapter popularAdapter;
    RecyclerView recyclerView, rv_categories, rv_area;
    MeaList meaList;
    CategoryAdapter categoryAdapter;
    CountryAdapter countryAdapter;


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
        rv_categories = (RecyclerView) view.findViewById(R.id.rv_categories);
        rv_area =(RecyclerView) view.findViewById(R.id.rv_area);

        homePresenter = new HomePresenter(this,MealsRepository.getInstance(MealsRemoteSourceImpl.getInstance(), MealsLocalSourceImpl.getInstance(getContext())),new FirebaseRepository(new FirebaseRemoteDataSource()));

        popularAdapter = new PopularAdapter(requireContext(),this, new ArrayList<>());
        categoryAdapter = new CategoryAdapter(requireContext(), new ArrayList<>(), this);
        countryAdapter = new CountryAdapter(this,new ArrayList<>());
        setRecycleViewForPopular();
        setRecycleViewForCategory();
        setRecycleViewForCountry();

        homePresenter.getRandoMeal();
        homePresenter.getMealsBasedOnCategory("Beef");
        homePresenter.getAllCategories("list");
        homePresenter.getAllCountries("list");

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

    private void setRecycleViewForPopular(){
        LinearLayoutManager linearLayoutManagerOne = new LinearLayoutManager(getContext());
        linearLayoutManagerOne.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManagerOne);
        recyclerView.setAdapter(popularAdapter);
        popularAdapter.notifyDataSetChanged();
    }

    private void setRecycleViewForCategory(){
        LinearLayoutManager linearLayoutManagerTwo = new LinearLayoutManager(getContext());
        linearLayoutManagerTwo.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_categories.setLayoutManager(linearLayoutManagerTwo);
        rv_categories.setAdapter(categoryAdapter);
       // categoryAdapter.notifyDataSetChanged();
    }

    private void setRecycleViewForCountry(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_area.setLayoutManager(linearLayoutManager);
        rv_area.setAdapter(countryAdapter);
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
    public void receiveAllCategoriesItems(List<CategoryItem> categoryList) {
        Log.d(TAG, "receiveAllCategoriesItems: ");
        categoryAdapter.setCategoryList(categoryList);
    }

    @Override
    public void receiveAllCountriesItems(List<CountryItem> areaList) {
        Log.d(TAG, "receiveAllCountriesItems: ");
        countryAdapter.setCountryList(areaList);
    }

    @Override
    public void doLogout() {
        //TODO NAVIGATE TO LOGIN SCREEN
        Toast.makeText(getContext(),"Logout Success!",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getActivity(), MainActivity.class));
        getActivity().finish();
    }

    @Override
    public void onCategoryItemClick(String categoryName) {
       Navigation.findNavController(getView())
               .navigate(HomeFragmentDirections.actionHomeFragmentToCategoryMealsFragment(categoryName));
    }

    @Override
    public void onCountryItemClick(String countryName) {
        Navigation.findNavController(getView())
                .navigate(HomeFragmentDirections.actionHomeFragmentToCountryMealsFragment(countryName));
    }

    @Override
    public void onPopularMealClicked(String mealID) {
        Intent intent = new Intent(getActivity(), MealDetailsActivity.class);
        intent.putExtra("MEAL_ID", mealID);
        startActivity(intent);
    }
}