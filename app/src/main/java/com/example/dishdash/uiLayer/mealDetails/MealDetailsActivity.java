package com.example.dishdash.uiLayer.mealDetails;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.dishdash.uiLayer.helper.Connection;
import com.example.dishdash.MainActivity;
import com.example.dishdash.R;
import com.example.dishdash.dataLayer.dataSource.localDataSource.MealsLocalSourceImpl;
import com.example.dishdash.dataLayer.dataSource.localDataSource.sharedPref.SharedPrefManager;
import com.example.dishdash.dataLayer.dataSource.localDataSource.sharedPref.SharedPreferenceLocalDataSource;
import com.example.dishdash.dataLayer.dataSource.remoteDataSource.mealsRemoteDataSource.classes.MealsRemoteSourceImpl;
import com.example.dishdash.dataLayer.dataSource.remoteDataSource.userRemoteDataSource.FirebaseRemoteDataSource;
import com.example.dishdash.dataLayer.model.pojo.mealsList.MealsItem;
import com.example.dishdash.dataLayer.repository.mealsRepo.MealsRepository;
import com.example.dishdash.dataLayer.repository.userRepo.FirebaseRepository;
import com.example.dishdash.uiLayer.helper.GlideImageLoader;
import com.example.dishdash.uiLayer.helper.ImageLoader;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

public class MealDetailsActivity extends AppCompatActivity implements IMealDetailsView, Connection.NetworkCallbacksListener {
    private ImageView iv_favourites_toolbar, iv_meal_details_category, iv_meal_details_area;
    private FloatingActionButton fab_favourite, fab_calender;
    private TextView tv_details_meal_category_name, tv_details_meal_area_name, tv_instruction_details;
    private RecyclerView rv_ingredient_measure;
    private MealDetailsPresenter mealDetailsPresenter;
    private DetailedMealAdapter detailedMealAdapter;
    private MealsItem meal;
    private CardView cv_youtube;
    private Connection connection;
    private WebView wv_youtube;
    private ConstraintLayout cl_whole_meal_detailed;
    private LottieAnimationView lottie_detaild_meal;
    private Intent inComingIntent;
    private ImageLoader imageLoader;
    private static final String CATEGORY_PHOTOS_URL = "https://www.themealdb.com/images/category/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_details);

        initUI();

        inComingIntent = getIntent();

        detailedMealAdapter = new DetailedMealAdapter(this, new ArrayList<>());

        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);

        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        rv_ingredient_measure.setLayoutManager(linearLayoutManager);

        rv_ingredient_measure.setAdapter(detailedMealAdapter);

        mealDetailsPresenter = new MealDetailsPresenter(this,
                MealsRepository.getInstance(MealsRemoteSourceImpl.getInstance(), MealsLocalSourceImpl.getInstance(this)),
                new FirebaseRepository(new FirebaseRemoteDataSource()),
                new SharedPrefManager(SharedPreferenceLocalDataSource.getInstance(this)));

        imageLoader = new GlideImageLoader(this);

        connection = new Connection(this, this);

        /* Check network state */
        if(!connection.isNetworkAvailable()) {
            onConnectionUnAvailable();
        }
        connection.register();

        setClickListenersForButtons();
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
        wv_youtube = (WebView) findViewById(R.id.wv_youtube);
        cv_youtube = (CardView) findViewById(R.id.cv_youtube);
        cl_whole_meal_detailed = (ConstraintLayout) findViewById(R.id.cl_whole_meal_detailed);
        lottie_detaild_meal = (LottieAnimationView) findViewById(R.id.lottie_detaild_meal);
    }

    private void setClickListenersForButtons(){
        fab_favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mealDetailsPresenter.checkUserMode(1);
            }
        });

        fab_calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mealDetailsPresenter.checkUserMode(2);
            }
        });
    }
    @Override
    public void getMealDetails(MealsItem meal) {
        this.meal = meal;
        if(meal.getStrMealThumb() != null){
            imageLoader.loadImage(meal.getStrMealThumb(), iv_favourites_toolbar);
        }

        if(meal.getStrCategory() != null){
            tv_details_meal_category_name.setText(meal.getStrCategory());
            imageLoader.loadImage(CATEGORY_PHOTOS_URL+meal.getStrCategory().toLowerCase()+".png",iv_meal_details_category );
        }

        if(meal.getStrArea() != null){
            showAreaPicture(meal.getStrArea().toLowerCase());
            tv_details_meal_area_name.setText(meal.getStrArea());
        }

        if(meal.getStrInstructions() != null){
            tv_instruction_details.setText(meal.getStrInstructions());
        }

        if (meal.getStrYoutube() != null) {
            String youtubeUrl = meal.getStrYoutube().replace("watch?v=", "embed/");
            WebSettings webSettings = wv_youtube.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setMediaPlaybackRequiresUserGesture(false);
            webSettings.setDomStorageEnabled(true);
            String html = "<iframe width=\"100%\" height=\"100%\" src=\"" + youtubeUrl + "\" frameborder=\"0\" allowfullscreen></iframe>";
            wv_youtube.loadData(html, "text/html", "utf-8");
            wv_youtube.setWebChromeClient(new WebChromeClient());
        } else{
            cv_youtube.setVisibility(GONE);
        }
        detailedMealAdapter.setIngredientsAndMeasures(meal.getIngredientsAndMeasures(meal));
    }

    @Override
    public void showInsetFavSuccess() {
        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showInsetFavFailed() {
        Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showAddedMealPlanSuccess() {
        Toast.makeText(this, "Added Successfully!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showAddedMealPlanFailed() {
        Toast.makeText(this, "Failed when add plan!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void saveToFavourites(String user_id) {
        mealDetailsPresenter.addMealToFavourites(user_id, meal);
    }

    @Override
    public void savePlannedMeal(String user_id) {
        openDatePicker(user_id);
    }

    @Override
    public void inGuestMode() {
        createDialog().show();
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

    private void openDatePicker(String user_id){
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(MealDetailsActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Log.d("TAG", "onDateSet: ");
                mealDetailsPresenter.addMealToWeeklyPlan(user_id, meal, mealDetailsPresenter.calcDate(year, monthOfYear, dayOfMonth));
            }
        }, year, month, day);

        datePickerDialog.setTitle("Select Date");
        datePickerDialog.getDatePicker().setMinDate(cldr.getTimeInMillis());
        datePickerDialog.show();
    }

    private AlertDialog createDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("You do not have account to logout, do you want to create account?");
        builder.setPositiveButton("Yes", ((dialog, which) -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            this.finish();
        }));

        builder.setNegativeButton("No", ((dialog, which) -> {
            Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
        }));
        return  builder.create();
    }

    private void showPageAndHideAnimation(){
        iv_favourites_toolbar.setVisibility(VISIBLE);
        fab_favourite.setVisibility(VISIBLE);
        fab_calender.setVisibility(VISIBLE);
        cl_whole_meal_detailed.setVisibility(VISIBLE);
        lottie_detaild_meal.setVisibility(GONE);
    }
    @Override
    public void onConnectionAvailable() {
        showPageAndHideAnimation();
        mealDetailsPresenter.getMeal(inComingIntent.getStringExtra("MEAL_ID"));
        connection.unregister();
    }

    private void hidePageAndShowAnimation(){
        iv_favourites_toolbar.setVisibility(GONE);
        fab_favourite.setVisibility(GONE);
        fab_calender.setVisibility(GONE);
        cl_whole_meal_detailed.setVisibility(GONE);
        lottie_detaild_meal.setVisibility(VISIBLE);
    }

    @Override
    public void onConnectionUnAvailable() {
        hidePageAndShowAnimation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        connection.unregister();
    }
}
