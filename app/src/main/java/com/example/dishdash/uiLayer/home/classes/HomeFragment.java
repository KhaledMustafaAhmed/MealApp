package com.example.dishdash.uiLayer.home.classes;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.example.dishdash.Connection;
import com.example.dishdash.HomeActivity;
import com.example.dishdash.MainActivity;
import com.example.dishdash.R;
import com.example.dishdash.dataLayer.dataSource.localDataSource.MealsLocalSourceImpl;
import com.example.dishdash.dataLayer.dataSource.localDataSource.sharedPref.SharedPrefManager;
import com.example.dishdash.dataLayer.dataSource.localDataSource.sharedPref.SharedPreferenceLocalDataSource;
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

public class HomeFragment extends Fragment implements IHomeView, ICategory, ICountry, IPopular, Connection.NetworkCallbacksListener {
    private static final String TAG = "HomeFragment";
    private Button btn_logout;
    private CardView cv_random_meal;
    private ImageView iv_random_meal;
    private TextView tv_random_meal_name;
    private HomePresenter homePresenter;
    private PopularAdapter popularAdapter;
    private CategoryAdapter categoryAdapter;
    private CountryAdapter countryAdapter;
    private RecyclerView rv_popular_meals, rv_categories, rv_area;
    private MeaList meaList;
    private ConstraintLayout cl_whole_home;
    private LottieAnimationView lottie_home_lost_connection;

    private Connection connection;

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ((HomeActivity) requireActivity()).showBottomNavigation(true);
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        connection = new Connection(requireContext(),this);
        cl_whole_home = view.findViewById(R.id.cl_whole_home);
        initUI(view);
        initAdapters();

        setRecycleViewForPopular();

        setRecycleViewForCategory();

        setRecycleViewForCountry();

        homePresenter = new HomePresenter(this,MealsRepository.getInstance(MealsRemoteSourceImpl.getInstance(),
                                             MealsLocalSourceImpl.getInstance(requireContext())),
                                            new FirebaseRepository(new FirebaseRemoteDataSource()),
                                            new SharedPrefManager(SharedPreferenceLocalDataSource.getInstance(requireContext())));

        String user =  homePresenter.checkUserMode();
        if(user.equals("GUEST")){
            btn_logout.setText("Login");
        }


        /* Check network state */
        if(!connection.isNetworkAvailable()) {
            onConnectionUnAvailable();
        }
        connection.register();

        cv_random_meal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MealDetailsActivity.class);
                intent.putExtra("MEAL_ID", meaList.getMeals().get(0).getIdMeal());
                startActivity(intent);
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String user =  homePresenter.checkUserMode();
                logoutCheckUserResponse(user);
            }
        });
    }

    private void initUI(View view){
        cv_random_meal= view.findViewById(R.id.cv_random_meal);
        iv_random_meal = view.findViewById(R.id.iv_random_meal);
        tv_random_meal_name = view.findViewById(R.id.tv_random_meal_name);
        rv_popular_meals = view.findViewById(R.id.rv_popular_meals);
        rv_categories = (RecyclerView) view.findViewById(R.id.rv_categories);
        rv_area =(RecyclerView) view.findViewById(R.id.rv_area);
        btn_logout = (Button) view.findViewById(R.id.btn_logout);
        lottie_home_lost_connection = (LottieAnimationView) view.findViewById(R.id.lottie_home_lost_connection);
    }

    private void initAdapters(){
        popularAdapter = new PopularAdapter(requireContext(),this, new ArrayList<>());
        categoryAdapter = new CategoryAdapter(requireContext(), new ArrayList<>(), this);
        countryAdapter = new CountryAdapter(this,new ArrayList<>());
    }

    private void setRecycleViewForPopular(){
        LinearLayoutManager linearLayoutManagerOne = new LinearLayoutManager(getContext());
        linearLayoutManagerOne.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_popular_meals.setLayoutManager(linearLayoutManagerOne);
        rv_popular_meals.setAdapter(popularAdapter);
    }

    private void setRecycleViewForCategory(){
        LinearLayoutManager linearLayoutManagerTwo = new LinearLayoutManager(getContext());
        linearLayoutManagerTwo.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_categories.setLayoutManager(linearLayoutManagerTwo);
        rv_categories.setAdapter(categoryAdapter);
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

        homePresenter.saveMealOfDay(meaList.getMeals().get(0).getIdMeal());

        tv_random_meal_name.setText(meaList.getMeals().get(0).getStrMeal());
        Glide.with(requireContext())
                .load(meaList.getMeals().get(0).getStrMealThumb())
                .into(iv_random_meal);
    }

    @Override
    public void receiveMealByID(MeaList meaList) {
        Log.d(TAG, "receiveMealByID: ");
        receiveRandoMeal(meaList);
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
        connection.unregister();
    }

    @Override
    public void receiveMealOfDayNews(boolean flag, String meal_id) {
        if(!flag){
            homePresenter.getRandoMeal();
            homePresenter.saveMealOfDay(meaList.getMeals().get(0).getIdMeal());
        }else{
            // TODO get meal by id here
        }
    }

    @Override
    public void doLogout() {
        createDialog("Do you want to logout?").show();
    }

    @Override
    public void guestLogout() {
        createDialog("You do not have account to logout, do you want to create account?").show();
    }

    @Override
    public void logoutCheckUserResponse(String user) {
        if(user.equals("GUEST")){
            Log.d(TAG, "logoutCheckUserResponse: "+ user);
            startActivity(new Intent(getActivity(), MainActivity.class));
        }else{
            Log.d(TAG, "logoutCheckUserResponse: "+ user);
            homePresenter.logout();
        }
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

    private AlertDialog createDialog(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(message);
        builder.setPositiveButton("Yes", ((dialog, which) -> {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        }));

        builder.setNegativeButton("No", ((dialog, which) -> {
            Toast.makeText(getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
        }));
        return  builder.create();
    }

    private void showLayoutAfterConnectionBack(){
        new Handler(Looper.getMainLooper()).post(() ->{
            lottie_home_lost_connection.setVisibility(GONE);
            cl_whole_home.setVisibility(VISIBLE);
        });
    }

    @Override
    public void onConnectionAvailable() {
        showLayoutAfterConnectionBack();
        homePresenter.checkMealOfTheDay();
        homePresenter.getMealsBasedOnCategory("Beef");
        homePresenter.getAllCategories("list");
        homePresenter.getAllCountries("list");
    }

    @Override
    public void onConnectionUnAvailable() {
        Log.d(TAG, "onConnectionUnAvailable: ");
        cl_whole_home.setVisibility(GONE);
        lottie_home_lost_connection.setVisibility(VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        connection.unregister();
    }
}