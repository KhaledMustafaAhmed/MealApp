package com.example.dishdash.uiLayer.search.classes;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.dishdash.uiLayer.helper.Connection;
import com.example.dishdash.uiLayer.helper.HomeActivity;
import com.example.dishdash.R;
import com.example.dishdash.dataLayer.dataSource.localDataSource.MealsLocalSourceImpl;
import com.example.dishdash.dataLayer.dataSource.remoteDataSource.mealsRemoteDataSource.classes.MealsRemoteSourceImpl;
import com.example.dishdash.dataLayer.model.pojo.areaCustomPojo.CountryItem;
import com.example.dishdash.dataLayer.model.pojo.categoryCustomPojo.CategoryItem;
import com.example.dishdash.dataLayer.model.pojo.ingredientsCustomPojo.IngredientItem;
import com.example.dishdash.dataLayer.repository.mealsRepo.MealsRepository;
import com.example.dishdash.uiLayer.home.adapters.CategoryAdapter;
import com.example.dishdash.uiLayer.home.adapters.CountryAdapter;
import com.example.dishdash.uiLayer.home.interfaces.ICategory;
import com.example.dishdash.uiLayer.home.interfaces.ICountry;
import com.example.dishdash.uiLayer.search.adapters.IngredientsAdapter;
import com.example.dishdash.uiLayer.search.interfaces.ISearchAdapter;
import com.example.dishdash.uiLayer.search.interfaces.ISearchVew;
import com.example.dishdash.uiLayer.helper.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements ISearchVew, ISearchAdapter, ICategory, ICountry, Connection.NetworkCallbacksListener {
    private SearchPresenter searchPresenter;
    private IngredientsAdapter ingredientsAdapter;
    private CategoryAdapter categoryAdapter;
    private CountryAdapter countryAdapter;
    private SearchView sv_search;
    private Connection connection;
    private RecyclerView rv_search_ingredients, rv_search_categories, rv_search_areas;
    private LottieAnimationView lottie_search_lost_connection;
    private TextView tv_search_ingredients, tv_search_categories, tv_search_areas;

    public SearchFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((HomeActivity) requireActivity()).showBottomNavigation(true);
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI(view);

        searchPresenter = new SearchPresenter(MealsRepository.getInstance(MealsRemoteSourceImpl.getInstance(),
                MealsLocalSourceImpl.getInstance(requireContext())), this);

        ingredientsAdapter = new IngredientsAdapter(requireContext(),new ArrayList<>(), this, new GlideImageLoader(requireContext()));

        categoryAdapter = new CategoryAdapter(requireContext(), new ArrayList<>(),this, new GlideImageLoader(requireContext()));

        countryAdapter = new CountryAdapter(this, new ArrayList<>());

        connection = new Connection(requireContext(), this);

        setUpRecycleView(new LinearLayoutManager(requireContext()), rv_search_ingredients);
        rv_search_ingredients.setAdapter(ingredientsAdapter);

        setUpRecycleView(new LinearLayoutManager(requireContext()), rv_search_categories);
        rv_search_categories.setAdapter(categoryAdapter);

        setUpRecycleView(new LinearLayoutManager(requireContext()), rv_search_areas);
        rv_search_areas.setAdapter(countryAdapter);

        /* Check network state */
        if(!connection.isNetworkAvailable()) {
            onConnectionUnAvailable();
        }

        connection.register();

        sv_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchPresenter.searchMeals(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchPresenter.searchMeals(newText);
                return false;
            }
        });

    }

    private void initUI(View view){
        sv_search = (SearchView) view.findViewById(R.id.sv_search);
        sv_search.clearFocus();
        rv_search_ingredients = (RecyclerView) view.findViewById(R.id.rv_search_ingredients);
        rv_search_categories = (RecyclerView) view.findViewById(R.id.rv_search_categories);
        rv_search_areas = (RecyclerView) view.findViewById(R.id.rv_search_areas);
        lottie_search_lost_connection = (LottieAnimationView) view.findViewById(R.id.lottie_search_lost_connection);
        tv_search_ingredients = (TextView) view.findViewById(R.id.tv_search_ingredients);
        tv_search_categories = (TextView) view.findViewById(R.id.tv_search_categories);
        tv_search_areas = (TextView) view.findViewById(R.id.tv_search_areas);
    }

    private void setUpRecycleView(LinearLayoutManager linearLayoutManager, RecyclerView recyclerView){
        linearLayoutManager = new LinearLayoutManager(requireContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
    @Override
    public void receiveIngredientList(List<IngredientItem> ingredientList) {
        ingredientsAdapter.setIngredientList(ingredientList);
    }

    @Override
    public void receiveCategoriesList(List<CategoryItem> categoryList) {
        categoryAdapter.setCategoryList(categoryList);
    }

    @Override
    public void receiveCountryList(List<CountryItem> countryList) {
        countryAdapter.setCountryList(countryList);
    }

    @Override
    public void onIngredientItemClick(String meal_name) {
        Navigation.findNavController(getView())
                .navigate(SearchFragmentDirections.actionSearchFragmentToIngredientMealsFragment(meal_name));
    }

    @Override
    public void onCategoryItemClick(String categoryName) {
        Navigation.findNavController(getView())
                .navigate(SearchFragmentDirections.actionSearchFragmentToCategoryMealsFragment(categoryName));
    }

    @Override
    public void onCountryItemClick(String countryName) {
        Navigation.findNavController(getView())
                .navigate(SearchFragmentDirections.actionSearchFragmentToCountryMealsFragment(countryName));
    }

    private void showPageAndHideAnimation(){
        new Handler(Looper.getMainLooper()).post(()->{
            tv_search_ingredients.setVisibility(VISIBLE);
            rv_search_ingredients.setVisibility(VISIBLE);
            tv_search_categories.setVisibility(VISIBLE);
            rv_search_categories.setVisibility(VISIBLE);
            tv_search_areas.setVisibility(VISIBLE);
            rv_search_areas.setVisibility(VISIBLE);
            lottie_search_lost_connection.setVisibility(GONE);
        });
    }
    @Override
    public void onConnectionAvailable() {
        showPageAndHideAnimation();
        searchPresenter.getAllIngredients("list");
        searchPresenter.getAllCategories("list");
        searchPresenter.getAllCountries("list");
        connection.unregister();
    }

    private void hidePageAndShowLoseConnection(){
        tv_search_ingredients.setVisibility(GONE);
        rv_search_ingredients.setVisibility(GONE);
        tv_search_categories.setVisibility(GONE);
        rv_search_categories.setVisibility(GONE);
        tv_search_areas.setVisibility(GONE);
        rv_search_areas.setVisibility(GONE);
        lottie_search_lost_connection.setVisibility(VISIBLE);
    }

    @Override
    public void onConnectionUnAvailable() {
        hidePageAndShowLoseConnection();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        connection.unregister();
    }
}