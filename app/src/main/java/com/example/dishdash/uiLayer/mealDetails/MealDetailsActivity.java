package com.example.dishdash.uiLayer.mealDetails;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dishdash.R;
import com.example.dishdash.dataLayer.dataSource.remoteDataSource.mealsRemoteDataSource.classes.MealsRemoteSourceImpl;
import com.example.dishdash.dataLayer.model.pojo.mealsList.MeaList;
import com.example.dishdash.dataLayer.model.pojo.mealsList.MealsItem;
import com.example.dishdash.dataLayer.repository.mealsRepo.MealsRepository;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MealDetailsActivity extends AppCompatActivity implements IMealDetailsView {
    AppBarLayout abl_favourites;

    ImageView iv_favourites_toolbar, iv_meal_details_category, iv_meal_details_area;

    FloatingActionButton fab_favourite, fab_calender;

    TextView tv_details_meal_category_name, tv_details_meal_area_name, tv_instruction_details;

    RecyclerView rv_ingredient_measure;
    MealDetailsPresenter mealDetailsPresenter;
    DetailedMealAdapter detailedMealAdapter;

    private static final String CATEGORY_PHOTOS_URL = "https://www.themealdb.com/images/category/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_details);
        initUI();
        detailedMealAdapter = new DetailedMealAdapter(this, new ArrayList<>());
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_ingredient_measure.setLayoutManager(linearLayoutManager);
        rv_ingredient_measure.setAdapter(detailedMealAdapter);
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

    private void setClickListenersForButtons(){
        fab_favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        fab_calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
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
            showAreaPicture(meal.getStrArea().toLowerCase());
            tv_details_meal_area_name.setText(meal.getStrArea());
        }

        if(meal.getStrInstructions() != null){
            tv_instruction_details.setText(meal.getStrInstructions());
        }
        detailedMealAdapter.setIngredientsAndMeasures(meal.getIngredientsAndMeasures(meal));
    }
    private void showAreaPicture(String area){
        switch (area){
            case "american": iv_meal_details_area.setImageResource(R.drawable.american); break;
            case "british": iv_meal_details_area.setImageResource(R.drawable.british); break;
            case "canadian": iv_meal_details_area.setImageResource(R.drawable.canadian); break;
            case "chinese": iv_meal_details_area.setImageResource(R.drawable.chinese); break;
            case "croatian": iv_meal_details_area.setImageResource(R.drawable.croatian); break;
            case "dutch": iv_meal_details_area.setImageResource(R.drawable.dutch); break;
            case "egyptian": iv_meal_details_area.setImageResource(R.drawable.egyptian); break;
            case "filipino": iv_meal_details_area.setImageResource(R.drawable.filipino); break;
            case "french": iv_meal_details_area.setImageResource(R.drawable.french); break;
            case "greek": iv_meal_details_area.setImageResource(R.drawable.greek); break;
            case "indian": iv_meal_details_area.setImageResource(R.drawable.indian); break;
            case "irish": iv_meal_details_area.setImageResource(R.drawable.irish); break;
            case "italian": iv_meal_details_area.setImageResource(R.drawable.italian); break;
            case "jamaican": iv_meal_details_area.setImageResource(R.drawable.jamaican); break;
            case "japanese": iv_meal_details_area.setImageResource(R.drawable.japanese); break;
            case "kenyan": iv_meal_details_area.setImageResource(R.drawable.kenyan); break;
            case "malaysian": iv_meal_details_area.setImageResource(R.drawable.malaysian); break;
            case "mexican": iv_meal_details_area.setImageResource(R.drawable.mexican); break;
            case "moroccan": iv_meal_details_area.setImageResource(R.drawable.moroccan); break;
            case "norwegian": iv_meal_details_area.setImageResource(R.drawable.norwegian); break;
            case "polish": iv_meal_details_area.setImageResource(R.drawable.polish); break;
            case "portuguese": iv_meal_details_area.setImageResource(R.drawable.portuguese); break;
            case "russian": iv_meal_details_area.setImageResource(R.drawable.russian); break;
            case "spanish": iv_meal_details_area.setImageResource(R.drawable.spanish); break;
            case "thai": iv_meal_details_area.setImageResource(R.drawable.thai); break;
            case "tunisian": iv_meal_details_area.setImageResource(R.drawable.tunisian); break;
            case "turkish": iv_meal_details_area.setImageResource(R.drawable.turkish); break;
            case "ukrainian": iv_meal_details_area.setImageResource(R.drawable.ukrainian); break;
            case "uruguayan": iv_meal_details_area.setImageResource(R.drawable.uruguayan); break;
            case "vietnamese": iv_meal_details_area.setImageResource(R.drawable.vietnamese); break;
        }
    }
}
