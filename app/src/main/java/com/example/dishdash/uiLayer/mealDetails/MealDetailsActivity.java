package com.example.dishdash.uiLayer.mealDetails;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dishdash.R;
import com.example.dishdash.dataLayer.dataSource.remoteDataSource.mealsRemoteDataSource.classes.MealsRemoteSourceImpl;
import com.example.dishdash.dataLayer.model.pojo.mealsList.MealsItem;
import com.example.dishdash.dataLayer.repository.mealsRepo.MealsRepository;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MealDetailsActivity extends AppCompatActivity implements IMealDetailsView {
    AppBarLayout abl_favourites;

    ImageView iv_favourites_toolbar, iv_meal_details_category, iv_meal_details_area;

    FloatingActionButton fab_favourite, fab_calender;

    TextView tv_details_meal_category_name, tv_details_meal_area_name, tv_instruction_details;

    RecyclerView rv_ingredient_measure;
    MealDetailsPresenter mealDetailsPresenter;

    private static final String CATEGORY_PHOTOS_URL = "https://www.themealdb.com/images/category/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_details);
        initUI();
        Intent inComingIntent = getIntent();
        mealDetailsPresenter = new MealDetailsPresenter(this,
                MealsRepository.getInstance(MealsRemoteSourceImpl.getInstance()));
        mealDetailsPresenter.getMeal(inComingIntent.getStringExtra("MEAL_ID"));

    }

    private void initUI(){
        iv_favourites_toolbar =(ImageView) findViewById(R.id.iv_favourites_toolbar);
        iv_meal_details_category =(ImageView) findViewById(R.id.iv_meal_details_category);
        iv_meal_details_area =(ImageView) findViewById(R.id.iv_meal_details_area);
        fab_favourite =(FloatingActionButton) findViewById(R.id.fab_favourite);
        fab_calender =(FloatingActionButton) findViewById(R.id.fab_calender);
        tv_details_meal_category_name =(TextView) findViewById(R.id.tv_details_meal_category_name);
        tv_details_meal_area_name =(TextView) findViewById(R.id.tv_details_meal_area_name);
        tv_instruction_details =(TextView) findViewById(R.id.tv_instruction_details);
        rv_ingredient_measure =(RecyclerView) findViewById(R.id.rv_ingredient_measure);
    }

    @Override
    public void getMealDetails(MealsItem meal) {
        if(meal.getStrMealThumb() != null){
            Glide.with(this)
                    .load(meal.getStrMealThumb())
                    .into(iv_favourites_toolbar);
        }

        if(meal.getStrCategory() != null){
            tv_details_meal_category_name.setText(meal.getStrCategory());
            Glide.with(this)
                    .load(CATEGORY_PHOTOS_URL+meal.getStrCategory().toLowerCase()+".png")
                    .into(iv_meal_details_category);
        }

        if(meal.getStrArea() != null){
            tv_details_meal_area_name.setText(meal.getStrArea());
        }

        if(meal.getStrInstructions() != null){
            tv_instruction_details.setText(meal.getStrInstructions());
        }
    }
}