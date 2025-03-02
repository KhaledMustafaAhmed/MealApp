package com.example.dishdash.uiLayer.mealsByIngredients.classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dishdash.R;
import com.example.dishdash.dataLayer.model.pojo.popularCustomPojo.PopularItem;
import com.example.dishdash.uiLayer.mealsByIngredients.interfaces.IIngredientMealsAdapter;

import java.util.List;

public class IngredientMealsAdapter extends RecyclerView.Adapter<IngredientMealsAdapter.IngredientViewHolder> {
    private List<PopularItem> meals;
    private Context context;
    private IIngredientMealsAdapter iIngredientMealsAdapter;

    public IngredientMealsAdapter(List<PopularItem> meals, Context context, IIngredientMealsAdapter iIngredientMealsAdapter) {
        this.meals = meals;
        this.context = context;
        this.iIngredientMealsAdapter = iIngredientMealsAdapter;
        notifyDataSetChanged();
    }

    public void setMeals(List<PopularItem> meals) {
        this.meals = meals;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public IngredientMealsAdapter.IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IngredientViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_meal_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientMealsAdapter.IngredientViewHolder holder, int position) {
        Glide.with(context)
                .load(meals.get(position).getStrMealThumb())
                .into(holder.iv_meal_by_ingredient_item);

        holder.tv_meal_by_ingredient_item.setText(meals.get(position).getStrMeal());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iIngredientMealsAdapter.onIngredientMealClicked(meals.get(position).getIdMeal());
            }
        });
    }

    @Override
    public int getItemCount() {
        return meals == null? 0: meals.size();
    }

    public class IngredientViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_meal_by_ingredient_item;
        TextView tv_meal_by_ingredient_item;
        public IngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_meal_by_ingredient_item = itemView.findViewById(R.id.iv_meal_by_ingredient_item);
            tv_meal_by_ingredient_item = itemView.findViewById(R.id.tv_meal_by_ingredient_item);
        }
    }
}
