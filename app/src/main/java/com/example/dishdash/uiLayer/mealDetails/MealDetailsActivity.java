package com.example.dishdash.uiLayer.mealDetails;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dishdash.R;
import com.example.dishdash.dataLayer.model.pojo.mealsList.MealsItem;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MealDetailsActivity extends AppCompatActivity implements IMealDetailsView {
    Intent inCominfIntent = getIntent();

    AppBarLayout abl_favourites;

    CollapsingToolbarLayout ctbl_favourites;

    ImageView iv_favourites_toolbar, iv_meal_details_category, iv_meal_details_area;

    Toolbar tb_favourites;

    FloatingActionButton fab_favourite, fab_calender;

    TextView tv_details_meal_category_name, tv_details_meal_area_name, tv_instruction_details;

    RecyclerView rv_ingredient_measure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_details);
        inCominfIntent.getStringExtra("MEAL_ID");
        initUI();
    }

    private void initUI(){
        abl_favourites = findViewById(R.id.abl_favourites);
        ctbl_favourites = findViewById(R.id.ctbl_favourites);
        iv_favourites_toolbar = findViewById(R.id.iv_favourites_toolbar);
        iv_meal_details_category = findViewById(R.id.iv_meal_details_category);
        iv_meal_details_area = findViewById(R.id.iv_meal_details_area);
        tb_favourites = findViewById(R.id.tb_favourites);
        fab_favourite = findViewById(R.id.fab_favourite);
        fab_calender = findViewById(R.id.fab_calender);
        tv_details_meal_category_name = findViewById(R.id.tv_details_meal_category_name);
        tv_details_meal_area_name = findViewById(R.id.tv_details_meal_area_name);
        tv_instruction_details = findViewById(R.id.tv_instruction_details);
        rv_ingredient_measure = findViewById(R.id.rv_ingredient_measure);
    }

    @Override
    public void getMealDetails(MealsItem meal) {
        
    }
}