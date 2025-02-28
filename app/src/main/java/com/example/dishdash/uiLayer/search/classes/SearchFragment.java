package com.example.dishdash.uiLayer.search.classes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements ISearchVew, ISearchAdapter, ICategory, ICountry {

    private SearchPresenter searchPresenter;
    private IngredientsAdapter ingredientsAdapter;
    private CategoryAdapter categoryAdapter;
    private CountryAdapter countryAdapter;
    private SearchView sv_search;
    private RecyclerView rv_search_ingredients, rv_search_categories, rv_search_areas;

    public SearchFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI(view);
        searchPresenter = new SearchPresenter(MealsRepository.getInstance(MealsRemoteSourceImpl.getInstance(),
                MealsLocalSourceImpl.getInstance(requireContext())), this);

        ingredientsAdapter = new IngredientsAdapter(requireContext(),new ArrayList<>(), this);

        categoryAdapter = new CategoryAdapter(requireContext(), new ArrayList<>(),this);

        countryAdapter = new CountryAdapter(this, new ArrayList<>());

        setUpRecycleView(new LinearLayoutManager(requireContext()), rv_search_ingredients);
        rv_search_ingredients.setAdapter(ingredientsAdapter);

        setUpRecycleView(new LinearLayoutManager(requireContext()), rv_search_categories);
        rv_search_categories.setAdapter(categoryAdapter);

        setUpRecycleView(new LinearLayoutManager(requireContext()), rv_search_areas);
        rv_search_areas.setAdapter(countryAdapter);


        searchPresenter.getAllIngredients("list");
        searchPresenter.getAllCategories("list");
        searchPresenter.getAllCountries("list");

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

    }

    @Override
    public void onCategoryItemClick(String categoryName) {

    }

    @Override
    public void onCountryItemClick(String countryName) {

    }
}